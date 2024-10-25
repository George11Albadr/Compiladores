package compiler.ast;

public class WhileStmt extends Statement {
    public Expression condition;
    public Block body;

    public WhileStmt(int line, int column, Expression condition, Block body) {
        super(line, column);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitWhileStmt(this);
    }
}