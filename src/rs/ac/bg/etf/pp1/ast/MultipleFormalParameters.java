// generated with ast extension for cup
// version 0.8
// 11/0/2020 15:27:12


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormalParameters extends FormalParameter {

    private FormalParameter FormalParameter;
    private OneFormParam OneFormParam;

    public MultipleFormalParameters (FormalParameter FormalParameter, OneFormParam OneFormParam) {
        this.FormalParameter=FormalParameter;
        if(FormalParameter!=null) FormalParameter.setParent(this);
        this.OneFormParam=OneFormParam;
        if(OneFormParam!=null) OneFormParam.setParent(this);
    }

    public FormalParameter getFormalParameter() {
        return FormalParameter;
    }

    public void setFormalParameter(FormalParameter FormalParameter) {
        this.FormalParameter=FormalParameter;
    }

    public OneFormParam getOneFormParam() {
        return OneFormParam;
    }

    public void setOneFormParam(OneFormParam OneFormParam) {
        this.OneFormParam=OneFormParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParameter!=null) FormalParameter.accept(visitor);
        if(OneFormParam!=null) OneFormParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParameter!=null) FormalParameter.traverseTopDown(visitor);
        if(OneFormParam!=null) OneFormParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParameter!=null) FormalParameter.traverseBottomUp(visitor);
        if(OneFormParam!=null) OneFormParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormalParameters(\n");

        if(FormalParameter!=null)
            buffer.append(FormalParameter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OneFormParam!=null)
            buffer.append(OneFormParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormalParameters]");
        return buffer.toString();
    }
}
