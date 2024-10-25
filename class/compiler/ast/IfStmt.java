package compiler.ast;

public class IfStmt extends Statement {
    public Expression condition;
    public Block thenBlock;
    public Block elseBlock;

    public IfStmt(int line, int column, Expression condition, Block thenBlock, Block elseBlock) {
        super(line, column);
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitIfStmt(this);
    }
}