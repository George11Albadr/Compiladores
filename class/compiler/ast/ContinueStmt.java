package compiler.ast;

public class ContinueStmt extends Statement {
    public ContinueStmt(int line, int column) {
        super(line, column);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitContinueStmt(this);
    }
}
