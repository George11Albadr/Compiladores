package compiler.ast;

public class ReturnStmt extends Statement {
    public Expression expr;

    public ReturnStmt(int line, int column, Expression expr) {
        super(line, column);
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitReturnStmt(this);
    }
}