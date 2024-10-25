package compiler.ast;

public class BinOp extends Expression {
    public String op;
    public Expression left;
    public Expression right;

    public BinOp(int line, int column, String op, Expression left, Expression right) {
        super(line, column);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitBinOp(this);
    }
}