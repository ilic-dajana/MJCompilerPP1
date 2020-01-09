// generated with ast extension for cup
// version 0.8
// 9/0/2020 14:14:54


package rs.ac.bg.etf.pp1.ast;

public class VariableDeclaration extends VarDecl {

    private Type Type;
    private VarNames VarNames;

    public VariableDeclaration (Type Type, VarNames VarNames) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarNames=VarNames;
        if(VarNames!=null) VarNames.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarNames getVarNames() {
        return VarNames;
    }

    public void setVarNames(VarNames VarNames) {
        this.VarNames=VarNames;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarNames!=null) VarNames.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarNames!=null) VarNames.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarNames!=null) VarNames.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VariableDeclaration(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarNames!=null)
            buffer.append(VarNames.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VariableDeclaration]");
        return buffer.toString();
    }
}
