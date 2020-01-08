package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	int getMainPc() {
		return mainPc;
	}
	
	public void chr(Obj c) {
		c.setAdr(Code.pc);
		Code.put(Code.return_);
	}
	
	public void ord(Obj o) {
		o.setAdr(Code.pc);
		Code.put(Code.return_);
	}
	
	public void len(Obj l) {
		l.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.put(Code.getfield);
		Code.put2(0);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ProgramIdent p) {
		Obj c = Tab.find("chr");
		Obj o = Tab.find("ord");
		Obj l = Tab.find("len");
		
		chr(c);
		ord(o);
		len(l);
		
	}
	
	public void visit(PrintOneStatement p) {
		if(p.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}else if(p.getExpr().struct == Tab.charType) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else {
			Code.loadConst(1);
			Code.put(Code.print);
		}
	}
	
	public void visit(PrintMultipleStatement p) {
		if(p.getExpr().struct == Tab.charType) {
			Code.loadConst(p.getNum());
			Code.put(Code.bprint);
		}else {
			Code.loadConst(p.getNum());
			Code.put(Code.print);
		}
	}
	
	public void visit(ReadStatement r) {
		if(r.getDesignator().obj.getType() == Tab.charType) {
			Code.put(Code.bread);
		}else {
			Code.put(Code.read);
		}
		Code.store(r.getDesignator().obj);
	}
	
	public void visit(FunctionCall f) {
		int t = f.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(t);
		
		if(f.getDesignator().obj.getType() != Tab.noType && f.getParent().getClass() == FunctionCallDesignator.class) {			
			Code.put(Code.pop);
		}
	}
	
	public void visit(EmptyReturnStatement r) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(ExpressionReturnStatement r) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(NumConstF f) {
		Obj con = Tab.insert(Obj.Con, "$", f.struct);
		con.setLevel(0);
		con.setAdr(f.getNum());
		Code.load(con);
	}
	
	public void visit(CharConstF f) {
		Obj con = Tab.insert(Obj.Con, "$", f.struct);
		con.setLevel(0);
		con.setAdr(f.getC());
		Code.load(con);
	}
	
	public void visit(BoolConstF f) {
		Obj con = Tab.insert(Obj.Con, "$", f.struct);
		con.setLevel(0);
		if(f.getB())
			con.setAdr(1);
		else
			con.setAdr(0);
		Code.load(con);
	}
	
	public void visit(MethodDeclaration m) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(MethodIdent m) {
		if("main".equalsIgnoreCase(m.getMethod())) {
			mainPc = Code.pc;
		}
		m.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(m.obj.getLevel());
		Code.put(m.obj.getLocalSymbols().size());
	}
	
	
	public void visit(DesignatorIdent m) {
		if(m.obj.getType().getKind() == Struct.Array) {
			Code.load(m.obj);
		}
	}
	
	public void visit(Designator d) {
		SyntaxNode parent = d.getParent();
		
		/*
		 if(d.obj.getType().getKind() == Struct.Array){
		 	Code.put(Code.pop);
		 }
		 */
		
		if(IncrementDesignator.class == parent.getClass()
				|| DecrementDesignator.class == parent.getClass()) {
			if(d.obj.getKind() == Obj.Elem) {
				Code.put(Code.dup2);
			}
		}
		if(AssignStatement.class != parent.getClass() && FunctionCall.class != parent.getClass()
				&& ReadStatement.class != parent.getClass() && d.obj.getType().getKind() != Struct.Array) {
			Code.load(d.obj);
		}
	}
	
	public void visit(IncrementDesignator i) {
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(i.getDesignator().obj);
	}
	
	public void visit(DecrementDesignator i) {
		Code.loadConst(-1);
		Code.put(Code.add);
		Code.store(i.getDesignator().obj);
	}
	
	public void visit(AssignStatement a) {
		Code.store(a.getDesignator().obj);
	}
	
	public void visit(AddExpression a) {
		if(a.getAddop().getClass() == Minus.class)
			Code.put(Code.sub);
		else
			Code.put(Code.add);
	}
	
	public void visit(AddTerm t) {
		if(t.getMulop().getClass() == Div.class)
			Code.put(Code.div);
		else if (t.getMulop().getClass() == Mod.class)
			Code.put(Code.rem);
		else
			Code.put(Code.mul);
	}
	
	public void visit(NegativeExpression e) {
		Code.put(Code.neg);
	}
	
	public void visit(Expression e) {
		
	}
	
	public void visit(MultipleCondition c) {
		Code.put(Code.mul);
	}
	
	
	
}
