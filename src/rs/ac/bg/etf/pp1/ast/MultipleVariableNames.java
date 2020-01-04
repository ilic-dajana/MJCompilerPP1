// generated with ast extension for cup
// version 0.8
// 4/0/2020 23:16:49


package rs.ac.bg.etf.pp1.ast;

public class MultipleVariableNames extends VarNames {

    private VarNames VarNames;
    private VarIdent VarIdent;

    public MultipleVariableNames (VarNames VarNames, VarIdent VarIdent) {
        this.VarNames=VarNames;
        if(VarNames!=null) VarNames.setParent(this);
        this.VarIdent=VarIdent;
        if(VarIdent!=null) VarIdent.setParent(this);
    }

    public VarNames getVarNames() {
        return VarNames;
    }

    public void setVarNames(VarNames VarNames) {
        this.VarNames=VarNames;
    }

    public VarIdent getVarIdent() {
        return VarIdent;
    }

    public void setVarIdent(VarIdent VarIdent) {
        this.VarIdent=VarIdent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarNames!=null) VarNames.accept(visitor);
        if(VarIdent!=null) VarIdent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarNames!=null) VarNames.traverseTopDown(visitor);
        if(VarIdent!=null) VarIdent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarNames!=null) VarNames.traverseBottomUp(visitor);
        if(VarIdent!=null) VarIdent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleVariableNames(\n");

        if(VarNames!=null)
            buffer.append(VarNames.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarIdent!=null)
            buffer.append(VarIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleVariableNames]");
        return buffer.toString();
    }
}
