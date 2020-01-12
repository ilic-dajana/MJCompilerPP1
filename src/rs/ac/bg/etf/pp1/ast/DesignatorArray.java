// generated with ast extension for cup
// version 0.8
// 12/0/2020 18:8:34


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArray extends DesignatorPart {

    private ArrayIndex ArrayIndex;

    public DesignatorArray (ArrayIndex ArrayIndex) {
        this.ArrayIndex=ArrayIndex;
        if(ArrayIndex!=null) ArrayIndex.setParent(this);
    }

    public ArrayIndex getArrayIndex() {
        return ArrayIndex;
    }

    public void setArrayIndex(ArrayIndex ArrayIndex) {
        this.ArrayIndex=ArrayIndex;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ArrayIndex!=null) ArrayIndex.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayIndex!=null) ArrayIndex.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayIndex!=null) ArrayIndex.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArray(\n");

        if(ArrayIndex!=null)
            buffer.append(ArrayIndex.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArray]");
        return buffer.toString();
    }
}
