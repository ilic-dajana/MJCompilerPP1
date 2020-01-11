// generated with ast extension for cup
// version 0.8
// 11/0/2020 15:27:12


package rs.ac.bg.etf.pp1.ast;

public class FORStatement extends Statement {

    private ForIdent ForIdent;
    private ForInit ForInit;
    private ForCondition ForCondition;
    private ForEnd ForEnd;
    private Statement Statement;

    public FORStatement (ForIdent ForIdent, ForInit ForInit, ForCondition ForCondition, ForEnd ForEnd, Statement Statement) {
        this.ForIdent=ForIdent;
        if(ForIdent!=null) ForIdent.setParent(this);
        this.ForInit=ForInit;
        if(ForInit!=null) ForInit.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForEnd=ForEnd;
        if(ForEnd!=null) ForEnd.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForIdent getForIdent() {
        return ForIdent;
    }

    public void setForIdent(ForIdent ForIdent) {
        this.ForIdent=ForIdent;
    }

    public ForInit getForInit() {
        return ForInit;
    }

    public void setForInit(ForInit ForInit) {
        this.ForInit=ForInit;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForEnd getForEnd() {
        return ForEnd;
    }

    public void setForEnd(ForEnd ForEnd) {
        this.ForEnd=ForEnd;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForIdent!=null) ForIdent.accept(visitor);
        if(ForInit!=null) ForInit.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForEnd!=null) ForEnd.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForIdent!=null) ForIdent.traverseTopDown(visitor);
        if(ForInit!=null) ForInit.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForEnd!=null) ForEnd.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForIdent!=null) ForIdent.traverseBottomUp(visitor);
        if(ForInit!=null) ForInit.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForEnd!=null) ForEnd.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FORStatement(\n");

        if(ForIdent!=null)
            buffer.append(ForIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForInit!=null)
            buffer.append(ForInit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEnd!=null)
            buffer.append(ForEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FORStatement]");
        return buffer.toString();
    }
}
