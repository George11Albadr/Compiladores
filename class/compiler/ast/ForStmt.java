package compiler.ast;

public class ForStmt extends Statement {
    public Statement init;
    public Expression condition;
    public Statement update;
    public Block body;

    public ForStmt(int line, int column, Statement init, Expression condition, Statement update, Block body) {
        super(line, column);
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitForStmt(this);
    }
}