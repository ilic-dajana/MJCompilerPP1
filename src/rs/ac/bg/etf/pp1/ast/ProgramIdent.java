// generated with ast extension for cup
// version 0.8
// 4/0/2020 22:9:59


package rs.ac.bg.etf.pp1.ast;

public class ProgramIdent extends ProgIdent {

    private String progname;

    public ProgramIdent (String progname) {
        this.progname=progname;
    }

    public String getProgname() {
        return progname;
    }

    public void setProgname(String progname) {
        this.progname=progname;
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
        buffer.append("ProgramIdent(\n");

        buffer.append(" "+tab+progname);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProgramIdent]");
        return buffer.toString();
    }
}
