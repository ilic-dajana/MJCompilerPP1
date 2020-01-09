// generated with ast extension for cup
// version 0.8
// 9/0/2020 14:14:54


package rs.ac.bg.etf.pp1.ast;

public class AddExpression extends AddopExpr {

    private AddopExpr AddopExpr;
    private Addop Addop;
    private Term Term;

    public AddExpression (AddopExpr AddopExpr, Addop Addop, Term Term) {
        this.AddopExpr=AddopExpr;
        if(AddopExpr!=null) AddopExpr.setParent(this);
        this.Addop=Addop;
        if(Addop!=null) Addop.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public AddopExpr getAddopExpr() {
        return AddopExpr;
    }

    public void setAddopExpr(AddopExpr AddopExpr) {
        this.AddopExpr=AddopExpr;
    }

    public Addop getAddop() {
        return Addop;
    }

    public void setAddop(Addop Addop) {
        this.Addop=Addop;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddopExpr!=null) AddopExpr.accept(visitor);
        if(Addop!=null) Addop.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddopExpr!=null) AddopExpr.traverseTopDown(visitor);
        if(Addop!=null) Addop.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddopExpr!=null) AddopExpr.traverseBottomUp(visitor);
        if(Addop!=null) Addop.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddExpression(\n");

        if(AddopExpr!=null)
            buffer.append(AddopExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Addop!=null)
            buffer.append(Addop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddExpression]");
        return buffer.toString();
    }
}
