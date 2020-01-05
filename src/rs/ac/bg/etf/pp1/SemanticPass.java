package rs.ac.bg.etf.pp1;

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
	private boolean returnFound;
	
	
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
	    	
	    	returnFound = false;
	    	currentMethod = null;
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
	 
	public void visit(VariableDeclaration v) {
		varDeclCount++;
		report_info("deklarisana je promenljiva: " + v.getVarNames(), v  );
	}

	public boolean passed() {
		return !errorDetected;
	}
	
}
