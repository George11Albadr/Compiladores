package compiler.ast;

public class Literal extends Expression {
    public Object value;

    public Literal(int line, int column, Object value) {
        super(line, column);
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitLiteral(this);
    }
}