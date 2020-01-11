// generated with ast extension for cup
// version 0.8
// 11/0/2020 15:27:12


package rs.ac.bg.etf.pp1.ast;

public class OneArrayInit extends ArrayInitList {

    private Dummy Dummy;
    private Expr Expr;

    public OneArrayInit (Dummy Dummy, Expr Expr) {
        this.Dummy=Dummy;
        if(Dummy!=null) Dummy.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public Dummy getDummy() {
        return Dummy;
    }

    public void setDummy(Dummy Dummy) {
        this.Dummy=Dummy;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Dummy!=null) Dummy.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Dummy!=null) Dummy.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Dummy!=null) Dummy.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OneArrayInit(\n");

        if(Dummy!=null)
            buffer.append(Dummy.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OneArrayInit]");
        return buffer.toString();
    }
}
