// generated with ast extension for cup
// version 0.8
// 11/0/2020 15:27:12


package rs.ac.bg.etf.pp1.ast;

public class MultipleDesignators extends Designators {

    private Designators Designators;
    private DesignatorPart DesignatorPart;

    public MultipleDesignators (Designators Designators, DesignatorPart DesignatorPart) {
        this.Designators=Designators;
        if(Designators!=null) Designators.setParent(this);
        this.DesignatorPart=DesignatorPart;
        if(DesignatorPart!=null) DesignatorPart.setParent(this);
    }

    public Designators getDesignators() {
        return Designators;
    }

    public void setDesignators(Designators Designators) {
        this.Designators=Designators;
    }

    public DesignatorPart getDesignatorPart() {
        return DesignatorPart;
    }

    public void setDesignatorPart(DesignatorPart DesignatorPart) {
        this.DesignatorPart=DesignatorPart;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designators!=null) Designators.accept(visitor);
        if(DesignatorPart!=null) DesignatorPart.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designators!=null) Designators.traverseTopDown(visitor);
        if(DesignatorPart!=null) DesignatorPart.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designators!=null) Designators.traverseBottomUp(visitor);
        if(DesignatorPart!=null) DesignatorPart.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleDesignators(\n");

        if(Designators!=null)
            buffer.append(Designators.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorPart!=null)
            buffer.append(DesignatorPart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleDesignators]");
        return buffer.toString();
    }
}
