package compiler.ast;

public class NewObject extends Expression {
    public String className;

    public NewObject(int line, int column, String className) {
        super(line, column);
        this.className = className;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitNewObject(this);
    }
}
