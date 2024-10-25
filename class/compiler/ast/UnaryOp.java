package compiler.ast;

public class UnaryOp extends Expression {
    public String op;
    public Expression expr;

    public UnaryOp(int line, int column, String op, Expression expr) {
        super(line, column);
        this.op = op;
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitUnaryOp(this);
    }
}