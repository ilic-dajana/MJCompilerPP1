// generated with ast extension for cup
// version 0.8
// 6/0/2020 18:42:36


package rs.ac.bg.etf.pp1.ast;

public class NegativeExpression extends Expr {

    private AddopExpr AddopExpr;

    public NegativeExpression (AddopExpr AddopExpr) {
        this.AddopExpr=AddopExpr;
        if(AddopExpr!=null) AddopExpr.setParent(this);
    }

    public AddopExpr getAddopExpr() {
        return AddopExpr;
    }

    public void setAddopExpr(AddopExpr AddopExpr) {
        this.AddopExpr=AddopExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AddopExpr!=null) AddopExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AddopExpr!=null) AddopExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AddopExpr!=null) AddopExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NegativeExpression(\n");

        if(AddopExpr!=null)
            buffer.append(AddopExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NegativeExpression]");
        return buffer.toString();
    }
}
