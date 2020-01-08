package rs.ac.bg.etf.pp1;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;
	public Struct boolType = new Struct(Struct.Bool);
	
	int printCallCount = 0;
	int varDeclCount = 0;
	int nVars = 0;
    Type arrayType = null;
	Type declaredType;
	Obj  currentMethod = null;
	boolean returnFound;
	int formalParamCnt = 0;
	boolean justInitied;
	int numOfExpr=0;
	int forLevelCnt=0;
	Deque<Obj> currentDesignators = new ArrayDeque<>();
	Deque<StringBuilder> designatorIdents = new ArrayDeque<>();
	Deque<CallFunctionStack> callFuncStack = new ArrayDeque<>();
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder sb = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			sb.append(" na liniji ").append(line);
		log.error(sb.toString());
	}
	
	public void report_info(String message, SyntaxNode info) {
		StringBuilder sb = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			sb.append(" na liniji ").append(line);
		log.error(sb.toString());
	}
	
	public void visit(PrintOneStatement p) {
		printCallCount++;
		Struct t = p.getExpr().struct;
		if( t != Tab.intType && t!= Tab.charType && t!= boolType) {
			report_error("Invalid type ", p);
		}		
	}
	
	public void visit(PrintMultipleStatement p) {
		printCallCount++;
		Struct t = p.getExpr().struct;
		if( t != Tab.intType && t!= Tab.charType && t!= boolType) {
			report_error("Invalid type ", p);
		}
		
		if(p.getNum() < 0) {
			report_error("Invalid number", p);
		}		
	}
	
	public void visit(ReadStatement r) {
		if(r.getDesignator().getDesignatorIdent().obj.getKind() != Obj.Var) {
			report_error("Parameter is not a variable! ", r );
		}
		Struct t = r.getDesignator().obj.getType();
		if(t != Tab.intType && t!= Tab.charType && t!=boolType ) {
			report_error("Invalid type ", r);
		}
	}
	
	public void visit(Program p) {
		Obj  mainObj = Tab.currentScope.findSymbol("main");
		if(mainObj == null)
			report_error("Main function not found ", null);
		nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(p.getProgIdent().obj);
    	Tab.closeScope();
	}
	
	public void visit (ProgramIdent p) {
		p.obj = Tab.insert(Obj.Prog, p.getProgname(), Tab.noType);
		Tab.openScope();
	}
	
	 public void visit(Type type){
	    	Obj typeNode = Tab.find(type.getType());
	  	if(typeNode == Tab.noObj){
	    		report_error("Nije pronadjen tip " + type.getType() + " u tabeli simbola! ", null);
	    		type.struct = Tab.noType;
	    	}else{
	    		if(Obj.Type == typeNode.getKind()){
	    			type.struct = typeNode.getType();
	    			if(type.getParent() instanceof ArrayInitList)
	    				arrayType = type;
	    		}else{
	    			report_error("Greska: Ime " + type.getType() + " ne predstavlja tip!", type);
	    			type.struct = Tab.noType;
	    		}
	    	}
	  	declaredType = type;
	  }
	
	 public void visit(MethodIdent methodIdent){
		 	ReturnType rt = methodIdent.getReturnType();
		 	if(rt instanceof VoidType)
		 		rt.struct = Tab.noType;
		 	else
		 			rt.struct = ((NonVoidType) rt).getType().struct;
		 	
		 	currentMethod = Tab.insert(Obj.Meth,  methodIdent.getMethod() , rt.struct);
		 	
	    	methodIdent.obj = currentMethod;
	    	Tab.openScope();
			report_info("In function  " + methodIdent.getMethod(), methodIdent);
	    }
	 
	 public void visit(MethodDeclaration methodDecl){
		 	if(currentMethod == null)
		 		return;
		 	
	    	if(!returnFound && currentMethod.getType() != Tab.noType){
				report_error("Method " +currentMethod.getName()  + " doesnt have return", methodDecl);
	    	}
	    	Tab.chainLocalSymbols(currentMethod);
	    	Tab.closeScope();
	    	currentMethod.setLevel(formalParamCnt);
	    	
	    	returnFound = false;
	    	currentMethod = null;
	    	formalParamCnt = 0;
	    	justInitied = false;
	    }
	 
	 public void visit(ConstNumber n) {
		 String name = n.getNumIdent();
		 Obj num = Tab.insert(Obj.Con, name, Tab.intType);
		 
		 int val = n.getNum();
		 num.setAdr(val);		 
	 }
	    
	 public void visit(ConstChar n) {
		 String name = n.getCharIdent();
		 Obj num = Tab.insert(Obj.Con, name, Tab.charType);
		 
		 char val = n.getC();
		 num.setAdr(val);		 
	 }   
	 
	 public void visit(ConstBool n) {
		 String name = n.getBoolIdent();
		 Obj num = Tab.insert(Obj.Con, name, boolType);
		 
		 Boolean val = n.getVal();
		 num.setAdr(val ? 1 : 0);		 
	 }
	 
	 public void visit(VariableIdent v) {
		 v.obj = Tab.insert(Obj.Var, v.getVar(), declaredType.struct);
	 }
	 
	 public void visit(ArrayVariableIdent v) {
		 Obj arrayObj = Tab.find(declaredType.getType() + "[]");
		 v.obj = Tab.insert(Obj.Var, v.getVar(),  arrayObj.getType());
	 }
	 
	 public void visit(ExpressionReturnStatement r) {
		 if(currentMethod == null) {
			 report_error("Return found outside function ", r);
		 }
		 returnFound = true;
		 Struct retType = currentMethod.getType();
		 
		 if(!r.getExpr().struct.assignableTo(retType)) {
			 report_error("Return type doesn't match", r);
		 }				
	 }
	 
	 public void visit(EmptyReturnStatement r) {
		 if(currentMethod == null) {
			 report_error("Return found outside function ", r);
		 }
		 returnFound = true;
		 
		 if(currentMethod.getType() != Tab.noType) {
			 report_error("Return type doesn't match", r);
		 }				
	 }
	 
	 public void visit(UsualParameter p) {
		 if(declaredType.struct == Tab.noType)
			 return;
		 if(justInitied) {
			 report_error("Non initied formal param ", p);
			 return;
		 }
		 formalParamCnt++;
		 String name  = p.getParam();
		 Type type = p.getType();
		 
		 if(Tab.currentScope().findSymbol(name)!= null) {
			 report_error("Symbol is already used in this function! ", p);
		 }else{
			 Obj o = Tab.insert(Obj.Var, name, declaredType.struct);
			 o.setFpPos(formalParamCnt);
			 report_info("Formal parameter found " + name, p);
		 }
	 }
	 
	 public void visit(ArrayParameter p) {
		 if(declaredType.struct == Tab.noType)
			 return;
		 if(justInitied) {
			 report_error("Non initied formal param ", p);
			 return;
		 }
		 formalParamCnt++;
		 String name  = p.getParam();
		 Type type = p.getType();
		 
		 if(Tab.currentScope().findSymbol(name)!= null) {
			 report_error("Symbol is already used in this function! ", p);
		 }else{
			 Obj o = Tab.insert(Obj.Var, name, declaredType.struct);
			 o.setFpPos(formalParamCnt);
			 report_info("Formal parameter found " + name, p);
		 }		 
	 }
	 
	 public void visit(NumberParameter p) {
		 if(p.getType().struct != Tab.intType)
			 report_error("Cant assign different types!", p);		 
		 if(!justInitied)
			 justInitied = true;
		 formalParamCnt++;
		 
		 Obj ob =Tab.find(p.getType().getType());
		 Obj o = Tab.insert(Obj.Var, p.getParam(), declaredType.struct);
		 o.setFpPos(formalParamCnt);
	 }
	 
	 public void visit(CharParameter p) {
		 if(p.getType().struct != Tab.charType)
			 report_error("Cant assign different types!", p);		 
		 if(!justInitied)
			 justInitied = true;
		 formalParamCnt++;
		 
		 Obj ob =Tab.find(p.getType().getType());
		 Obj o = Tab.insert(Obj.Var, p.getParam(), declaredType.struct);
		 o.setFpPos(formalParamCnt);
		 
	 }
 
	 public void visit(BoolParameter p) {
		 if(p.getType().struct != boolType)
			 report_error("Cant assign different types!", p);		 
		 if(!justInitied)
			 justInitied = true;
		 formalParamCnt++;
		 
		 Obj ob =Tab.find(p.getType().getType());
		 Obj o = Tab.insert(Obj.Var, p.getParam(), declaredType.struct);
		 o.setFpPos(formalParamCnt);
		 
	 }
	 
	 
	 
	 public void visit(Expression e) {
		 e.struct = e.getAddopExpr().struct;
	 }

	 public void visit(NegativeExpression e) {
		 e.struct = e.getAddopExpr().struct;
		 if(e.struct != Tab.intType) {
			 report_error("Can't negate non int type ", e);
			 e.struct = Tab.noType;
			 return;
		 }
	 }
	 
	 public void visit(AddExpression e) {
		 Struct s1 = e.getTerm().struct;
		 Struct s2 = e.getAddopExpr().struct;
		 
		 if(s1.equals(s2) && s1 == Tab.intType) {
			 e.struct = Tab.intType;
		 }else {
			 e.struct = Tab.noType;
			 report_error("Invalid expression ", e);
		 }		 
	 }

	 public void visit(OneAddExpression e) {
		 e.struct = e.getTerm().struct;
	 }
	 
	 public void visit(AddTerm e) {
		 Struct s1 = e.getTerm().struct;
		 Struct s2 = e.getFactor().struct;
		 
		 if(!(s1.equals(s2)) ||  s1 != Tab.intType) {
			 e.struct = Tab.noType;
			 report_error("Invalid expression ", e);
		 }else {
			 e.struct = Tab.intType;
		 }		 
	 }
	 
	 public void visit(OneMulFactor t) {
		 t.struct = t.getFactor().struct;
	 }
	 
	 public void visit(DesignatorF f) {
		 f.struct = f.getDesignator().obj.getType();
	 }
	 
	 public void visit(NumConstF f) {
		 f.struct = Tab.intType;
	 }

	 public void visit(CharConstF f) {
		 f.struct = Tab.charType;
	 }
	 
	 public void visit(BoolConstF f) {
		 f.struct = boolType;
	 }
	 
	 public void visit(NewF f) {
		 // da li mi treba uopste ?
	 }
	 
	 public void visit(NewArrayF f) {
		 if(f.getExpr().struct != Tab.intType) {
			 report_error("Invalid array size type ", f);
			 return;
		 }
		 Obj array = Tab.find(f.getType().getType() + "[]");
		 f.struct = array.getType();
	 }
	 
	 public void visit(NewArrayWithInitListF f) {
		 if(f.getExpr().struct != Tab.intType) {
			 report_error("Invalid array size type ", f);
			 return;
		 }
	 }
	 
	 public void visit(FunctionCallF f) {
		 DesignatorIdent ident = f.getFunctionCall().getDesignator().getDesignatorIdent();
		 if(ident.obj.getKind() != Obj.Meth) {
			 report_error("Function call invalid ", f);
		 }else {
			 f.struct = f.getFunctionCall().getDesignator().obj.getType();
		 }
	 }
	 
	 public void visit(ComplexExpressionF f) {
		 f.struct = f.getExpr().struct;
	 }
	 
	 public void visit(MultipleArrayInit m) {
		 if(m.getExpr().struct != arrayType.struct)
			 report_error("Invalid type ", m);
		 numOfExpr++;
	 }
	 
	 public void visit(OneArrayInit m) {
		 if(m.getExpr().struct != arrayType.struct)
			 report_error("Invalid type ", m);
		 numOfExpr++;
	 }
	 
	 public void visit(IncrementDesignator d) {
		 if(d.getDesignator().getDesignatorIdent().obj.getKind() != Obj.Var
			|| d.getDesignator().obj.getType() != Tab.intType	 ) {
			 report_error("Increment expression invalid ", d);
		 }
	 }
	 
	 public void visit(DecrementDesignator d) {
		 if(d.getDesignator().getDesignatorIdent().obj.getKind() != Obj.Var
					|| d.getDesignator().obj.getType() != Tab.intType	 ) {
					 report_error("Increment expression invalid ", d);
				 }
		 }
 
	 public void visit(AssignStatement d) {
		 if(d.getDesignator().getDesignatorIdent().obj.getKind() != Obj.Var) {
			 report_error("Designator is not var type!", d);
			 return;
		 }
		 if(!d.getExpr().struct.assignableTo(d.getDesignator().obj.getType())) {
			 report_error("Invalid types ", d);
		 }
	 }
	 
	 public void visit(FunctionCallDesignator f) {
		 if(f.getFunctionCall().getDesignator().getDesignatorIdent().obj.getKind() !=  Obj.Meth) {
			 report_error("This is not a function! ", f);
		 }
	 }
	 
	 public void visit(ForIdent f) {
		 forLevelCnt++;
	 }
	 
	 public void visit(BreakStatement b) {
		 if(forLevelCnt == 0)
			 report_error("Found break outside for loop", b);
	 }
	 
	 public void visit(ContinueStatement b) {
		 if(forLevelCnt == 0)
			 report_error("Found continue outside for loop", b);
	 }
	 
	 public void visit(FORStatement f) {
		 forLevelCnt--;
	 }
	 
	 public void visit(NonEmptyForCondition f) {
		 if(f.getCondition().struct != boolType) {
			 report_error("Not a bool Type condition! ", f);
		 }
	 }
	 
	 public void visit(EmptyForCondition f) {
		
	 }
	 public void visit(OneCondition c) {
		 c.struct = c.getCondTerm().struct;
	 }
	 
	 public void visit(MultipleCondition m) {
		 Struct c1 = m.getCondTerm().struct;
		 Struct c2 = m.getCondition().struct;
		 
		 if(!c1.equals(c2)) {
			 report_error("Conditions must be the sam type", m);
		 }
		 m.struct = c1;
	 }
	 
	 public void visit(OneConditionTerm c) {
		 c.struct = c.getCondFact().struct;
	 }
	 
	 public void visit(MultipleConditionTerms m) {
		 Struct c1 = m.getCondTerm().struct;
		 Struct c2 = m.getCondFact().struct;
		 
		 if(!c1.equals(c2)) {
			 report_error("Conditions must be the sam type", m);
		 }
		 m.struct = c1;
	 }
	 
	 public void visit(ExpressionConditionFact e) {
		 if(e.getExpr().struct != boolType) {
			 report_error("Must be boolType ", e);
		 }
	 }
	 
	 public void visit(RelOpConditionFact e) {
		 Struct e1 = e.getExpr().struct;
		 Struct e2 = e.getExpr1().struct;
		 Relop rel = e.getRelop();
		 
		 if(!e1.compatibleWith(e2)) {
			 report_error("Invalid type of expression! ", e);
			 return;
		 }else {			
			 if((e1.getKind() == Struct.Array || e2.getKind() == Struct.Array) 
					 && (!(rel instanceof RelopEQ) && !(rel instanceof RelopNEQ)  )) {
				 report_error("Rel operator not valid ", e);
				 return;
			 }
		 }
		 e.struct = boolType;
	 }
	 
	 public void visit(DesignatorIdent d) {
		 String ime = d.getDes();
		 Obj objD = Tab.find(ime);
		 currentDesignators.addFirst(objD);
		 d.obj = objD;
		 if(objD == Tab.noObj) {
			 report_error("Missing declaration ", d);
			 currentDesignators.removeFirst();
			 currentDesignators.addFirst(Tab.noObj);
		 }else {
			 designatorIdents.addFirst(new StringBuilder(ime));
		 }
	 }
	 
	 public void visit(Designator d) {
		 String ime = designatorIdents.peekFirst().toString();
		 d.obj = currentDesignators.removeFirst();
		 designatorIdents.removeFirst();
		 
		 if(d.getParent().getClass() == FunctionCall.class) {
			 if(currentMethod != null && currentMethod.getName() == d.obj.getName()) {
				 Tab.chainLocalSymbols(currentMethod);
				 callFuncStack.addFirst(new CallFunctionStack(formalParamCnt, currentMethod.getLocalSymbols()));
			 }else {
				 callFuncStack.addFirst(new CallFunctionStack(d.getDesignatorIdent().obj));
			 }
		 }
	 }
	 
	 public void visit(DesignatorArray d) {
		 if(currentDesignators.peekFirst() == Tab.noObj)
			 return;
		 
		 int desKind = currentDesignators.peekFirst().getType().getKind();
		 
		 if(desKind != Struct.Array || d.getExpr().struct != Tab.intType) {
			 currentDesignators.removeFirst();
			 currentDesignators.addFirst(Tab.noObj);
			 report_error("Invalid expression ", d);
			 return;
		 }
		 Obj objDes = currentDesignators.removeFirst();
		 currentDesignators.addFirst(new Obj(Obj.Elem, designatorIdents.peekFirst().toString(), objDes.getType().getElemType()));
		 designatorIdents.peekFirst().append("[]");
	 }
	 
	 public void visit(FunctionCall f) {
		 callFuncStack.removeFirst();
	 }
	 
	 public void visit(ActualParameters p) {
		 if(!(callFuncStack.peekFirst().getnParam() > callFuncStack.peekFirst().getnParamObradjeno())) {
			 report_error("Function requires " + callFuncStack.peekFirst().getnParam() + " parameters ", p);
		 }
	 }
	 
	 public void visit(EmptyParameters p) {
			 if(callFuncStack.peekFirst().getnParam() != 0)
				 report_error("Function doesnt require parameters", p);
	  }
	 
	 public void visit(MultipleActualParameters p) {
		 if(callFuncStack.peekFirst().finished()) {
			 report_error("Function requires " + callFuncStack.peekFirst().getnParam() + " parameters ", p);
			 return;
		 }
		 int nParam = callFuncStack.peekFirst().getnParamObradjeno() + 1;
		 callFuncStack.peekFirst().setnParamObradjeno(nParam);
		 
		 for(Obj o:callFuncStack.peekFirst().getLocalParam()) {
			 if(o.getFpPos() == nParam) {
				if(!p.getExpr().struct.assignableTo(o.getType())) {
					report_error("Invalid types ", p);
				}
			 }
		 }
	 }
	 
	 public void visit(OneActualParameter p) {
		 if(callFuncStack.peekFirst().finished()) {
			 report_error("Function requires " + callFuncStack.peekFirst().getnParam() + " parameters ", p);
			 return;	
		 }
		 
		 int nParam = callFuncStack.peekFirst().getnParamObradjeno() + 1;
		 callFuncStack.peekFirst().setnParamObradjeno(nParam);
		 
		 for(Obj o:callFuncStack.peekFirst().getLocalParam()) {
			 if(o.getFpPos() == nParam) {
				if(!p.getExpr().struct.assignableTo(o.getType())) {
					report_error("Invalid types ", p);
				}
			 }
		 }
	 }
	 
	public void visit(VariableDeclaration v) {
		varDeclCount++;
		report_info("deklarisana je promenljiva: " + v.getVarNames(), v  );
	}

	public boolean passed() {
		return !errorDetected;
	}
	
}
