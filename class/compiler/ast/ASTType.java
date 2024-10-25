package compiler.ast;

public class ASTType extends ASTNode {
    public String typeName;

    public ASTType(int line, int column, String typeName) {
        super(line, column);
        this.typeName = typeName;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitASTType(this);
    }
}