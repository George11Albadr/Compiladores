package compiler.ast;

public abstract class Statement extends ASTNode {
    public Statement(int line, int column) {
        super(line, column);
    }

    @Override
    public abstract void accept(ASTVisitor visitor);
}