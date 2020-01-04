// generated with ast extension for cup
// version 0.8
// 4/0/2020 23:16:49


package rs.ac.bg.etf.pp1.ast;

public class FunctionCallDesignator extends DesignatorStatement {

    private FunctionCall FunctionCall;

    public FunctionCallDesignator (FunctionCall FunctionCall) {
        this.FunctionCall=FunctionCall;
        if(FunctionCall!=null) FunctionCall.setParent(this);
    }

    public FunctionCall getFunctionCall() {
        return FunctionCall;
    }

    public void setFunctionCall(FunctionCall FunctionCall) {
        this.FunctionCall=FunctionCall;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FunctionCall!=null) FunctionCall.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FunctionCall!=null) FunctionCall.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FunctionCall!=null) FunctionCall.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FunctionCallDesignator(\n");

        if(FunctionCall!=null)
            buffer.append(FunctionCall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FunctionCallDesignator]");
        return buffer.toString();
    }
}
