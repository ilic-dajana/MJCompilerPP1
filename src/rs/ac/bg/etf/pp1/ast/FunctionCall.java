// generated with ast extension for cup
// version 0.8
// 12/0/2020 18:8:34


package rs.ac.bg.etf.pp1.ast;

public class FunctionCall implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Designator Designator;
    private FunctionCallParameter FunctionCallParameter;

    public FunctionCall (Designator Designator, FunctionCallParameter FunctionCallParameter) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FunctionCallParameter=FunctionCallParameter;
        if(FunctionCallParameter!=null) FunctionCallParameter.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FunctionCallParameter getFunctionCallParameter() {
        return FunctionCallParameter;
    }

    public void setFunctionCallParameter(FunctionCallParameter FunctionCallParameter) {
        this.FunctionCallParameter=FunctionCallParameter;
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
        if(Designator!=null) Designator.accept(visitor);
        if(FunctionCallParameter!=null) FunctionCallParameter.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FunctionCallParameter!=null) FunctionCallParameter.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FunctionCallParameter!=null) FunctionCallParameter.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FunctionCall(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FunctionCallParameter!=null)
            buffer.append(FunctionCallParameter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FunctionCall]");
        return buffer.toString();
    }
}
