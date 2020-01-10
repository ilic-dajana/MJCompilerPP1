// generated with ast extension for cup
// version 0.8
// 10/0/2020 19:34:32


package rs.ac.bg.etf.pp1.ast;

public class ConstBool extends Constant {

    private String boolIdent;
    private Boolean val;

    public ConstBool (String boolIdent, Boolean val) {
        this.boolIdent=boolIdent;
        this.val=val;
    }

    public String getBoolIdent() {
        return boolIdent;
    }

    public void setBoolIdent(String boolIdent) {
        this.boolIdent=boolIdent;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val=val;
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
        buffer.append("ConstBool(\n");

        buffer.append(" "+tab+boolIdent);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstBool]");
        return buffer.toString();
    }
}
