// generated with ast extension for cup
// version 0.8
// 4/0/2020 23:16:49


package rs.ac.bg.etf.pp1.ast;

public class MethodIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String method;

    public MethodIdent (String method) {
        this.method=method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method=method;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("MethodIdent(\n");

        buffer.append(" "+tab+method);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodIdent]");
        return buffer.toString();
    }
}
