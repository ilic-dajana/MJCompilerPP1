// generated with ast extension for cup
// version 0.8
// 10/0/2020 19:34:32


package rs.ac.bg.etf.pp1.ast;

public class MultipleArrayInit extends ArrayInitList {

    private ArrayInitList ArrayInitList;
    private Dummy Dummy;
    private Expr Expr;

    public MultipleArrayInit (ArrayInitList ArrayInitList, Dummy Dummy, Expr Expr) {
        this.ArrayInitList=ArrayInitList;
        if(ArrayInitList!=null) ArrayInitList.setParent(this);
        this.Dummy=Dummy;
        if(Dummy!=null) Dummy.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public ArrayInitList getArrayInitList() {
        return ArrayInitList;
    }

    public void setArrayInitList(ArrayInitList ArrayInitList) {
        this.ArrayInitList=ArrayInitList;
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
        if(ArrayInitList!=null) ArrayInitList.accept(visitor);
        if(Dummy!=null) Dummy.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayInitList!=null) ArrayInitList.traverseTopDown(visitor);
        if(Dummy!=null) Dummy.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayInitList!=null) ArrayInitList.traverseBottomUp(visitor);
        if(Dummy!=null) Dummy.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleArrayInit(\n");

        if(ArrayInitList!=null)
            buffer.append(ArrayInitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

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
        buffer.append(") [MultipleArrayInit]");
        return buffer.toString();
    }
}
