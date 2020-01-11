// generated with ast extension for cup
// version 0.8
// 11/0/2020 15:27:12


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclaration extends MethodDecl {

    private MethodIdent MethodIdent;
    private FormParams FormParams;
    private VarDeclList VarDeclList;
    private Statements Statements;

    public MethodDeclaration (MethodIdent MethodIdent, FormParams FormParams, VarDeclList VarDeclList, Statements Statements) {
        this.MethodIdent=MethodIdent;
        if(MethodIdent!=null) MethodIdent.setParent(this);
        this.FormParams=FormParams;
        if(FormParams!=null) FormParams.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.Statements=Statements;
        if(Statements!=null) Statements.setParent(this);
    }

    public MethodIdent getMethodIdent() {
        return MethodIdent;
    }

    public void setMethodIdent(MethodIdent MethodIdent) {
        this.MethodIdent=MethodIdent;
    }

    public FormParams getFormParams() {
        return FormParams;
    }

    public void setFormParams(FormParams FormParams) {
        this.FormParams=FormParams;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public Statements getStatements() {
        return Statements;
    }

    public void setStatements(Statements Statements) {
        this.Statements=Statements;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodIdent!=null) MethodIdent.accept(visitor);
        if(FormParams!=null) FormParams.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(Statements!=null) Statements.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodIdent!=null) MethodIdent.traverseTopDown(visitor);
        if(FormParams!=null) FormParams.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(Statements!=null) Statements.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodIdent!=null) MethodIdent.traverseBottomUp(visitor);
        if(FormParams!=null) FormParams.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(Statements!=null) Statements.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclaration(\n");

        if(MethodIdent!=null)
            buffer.append(MethodIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParams!=null)
            buffer.append(FormParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statements!=null)
            buffer.append(Statements.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclaration]");
        return buffer.toString();
    }
}
