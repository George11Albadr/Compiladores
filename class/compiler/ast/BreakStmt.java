package compiler.ast;

public class BreakStmt extends Statement {
    public BreakStmt(int line, int column) {
        super(line, column);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitBreakStmt(this);
    }
}