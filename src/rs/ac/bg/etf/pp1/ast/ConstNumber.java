// generated with ast extension for cup
// version 0.8
// 4/0/2020 23:16:49


package rs.ac.bg.etf.pp1.ast;

public class ConstNumber extends Constant {

    private String numIdent;
    private Integer num;

    public ConstNumber (String numIdent, Integer num) {
        this.numIdent=numIdent;
        this.num=num;
    }

    public String getNumIdent() {
        return numIdent;
    }

    public void setNumIdent(String numIdent) {
        this.numIdent=numIdent;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num=num;
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
        buffer.append("ConstNumber(\n");

        buffer.append(" "+tab+numIdent);
        buffer.append("\n");

        buffer.append(" "+tab+num);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstNumber]");
        return buffer.toString();
    }
}
