package compiler.ast;

public class Param extends ASTNode {
    public String id;
    public ASTType type;

    public Param(int line, int column, String id, ASTType type) {
        super(line, column);
        this.id = id;
        this.type = type;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitParam(this);
    }
}