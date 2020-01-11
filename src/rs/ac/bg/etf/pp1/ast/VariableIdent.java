// generated with ast extension for cup
// version 0.8
// 11/0/2020 15:27:12


package rs.ac.bg.etf.pp1.ast;

public class VariableIdent extends VarIdent {

    private String var;

    public VariableIdent (String var) {
        this.var=var;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var=var;
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
        buffer.append("VariableIdent(\n");

        buffer.append(" "+tab+var);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableIdent]");
        return buffer.toString();
    }
}
