// generated with ast extension for cup
// version 0.8
// 4/0/2020 23:16:49


package rs.ac.bg.etf.pp1.ast;

public class ConstChar extends Constant {

    private String charIdent;
    private Character c;

    public ConstChar (String charIdent, Character c) {
        this.charIdent=charIdent;
        this.c=c;
    }

    public String getCharIdent() {
        return charIdent;
    }

    public void setCharIdent(String charIdent) {
        this.charIdent=charIdent;
    }

    public Character getC() {
        return c;
    }

    public void setC(Character c) {
        this.c=c;
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
        buffer.append("ConstChar(\n");

        buffer.append(" "+tab+charIdent);
        buffer.append("\n");

        buffer.append(" "+tab+c);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstChar]");
        return buffer.toString();
    }
}
