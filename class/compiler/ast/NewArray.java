package compiler.ast;

public class NewArray extends Expression {
    public ASTType type;
    public Expression size;

    public NewArray(int line, int column, ASTType type, Expression size) {
        super(line, column);
        this.type = type;
        this.size = size;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitNewArray(this);
    }
}