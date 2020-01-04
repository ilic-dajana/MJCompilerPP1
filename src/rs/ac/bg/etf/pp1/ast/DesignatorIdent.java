// generated with ast extension for cup
// version 0.8
// 4/0/2020 22:9:59


package rs.ac.bg.etf.pp1.ast;

public class DesignatorIdent implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String des;

    public DesignatorIdent (String des) {
        this.des=des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des=des;
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
        buffer.append("DesignatorIdent(\n");

        buffer.append(" "+tab+des);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorIdent]");
        return buffer.toString();
    }
}
