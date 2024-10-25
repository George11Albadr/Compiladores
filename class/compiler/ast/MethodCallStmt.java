package compiler.ast;

public class MethodCallStmt extends Statement {
    public MethodCall call;

    public MethodCallStmt(int line, int column, MethodCall call) {
        super(line, column);
        this.call = call;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitMethodCallStmt(this);
    }
}
