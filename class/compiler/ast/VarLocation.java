package compiler.ast;

public class VarLocation extends Expression {
    public String id;
    public Expression index;

    public VarLocation(int line, int column, String id, Expression index) {
        super(line, column);
        this.id = id;
        this.index = index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitVarLocation(this);
    }
}