// generated with ast extension for cup
// version 0.8
// 6/0/2020 18:42:36


package rs.ac.bg.etf.pp1.ast;

public class BoolParameter extends OneFormParam {

    private Type Type;
    private String param;
    private Boolean b;

    public BoolParameter (Type Type, String param, Boolean b) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.param=param;
        this.b=b;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param=param;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b=b;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolParameter(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+param);
        buffer.append("\n");

        buffer.append(" "+tab+b);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolParameter]");
        return buffer.toString();
    }
}
