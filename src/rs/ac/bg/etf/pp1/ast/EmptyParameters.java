// generated with ast extension for cup
// version 0.8
// 6/0/2020 18:42:36


package rs.ac.bg.etf.pp1.ast;

public class EmptyParameters extends FunctionCallParameter {

    public EmptyParameters () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EmptyParameters(\n");

        buffer.append(tab);
        buffer.append(") [EmptyParameters]");
        return buffer.toString();
    }
}
