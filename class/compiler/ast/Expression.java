package compiler.ast;

public abstract class Expression extends ASTNode {
    public Expression(int line, int column) {
        super(line, column);
    }

    @Override
    public abstract void accept(ASTVisitor visitor);
}