// generated with ast extension for cup
// version 0.8
// 9/0/2020 14:14:54


package rs.ac.bg.etf.pp1.ast;

public class NumberParameter extends OneFormParam {

    private Type Type;
    private String param;
    private Integer n;

    public NumberParameter (Type Type, String param, Integer n) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.param=param;
        this.n=n;
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

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n=n;
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
        buffer.append("NumberParameter(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+param);
        buffer.append("\n");

        buffer.append(" "+tab+n);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumberParameter]");
        return buffer.toString();
    }
}
