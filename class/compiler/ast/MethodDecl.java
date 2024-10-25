package compiler.ast;

import java.util.List;

public class MethodDecl extends ASTNode {
    public String id;
    public ASTType returnType;
    public List<Param> params;
    public Block body;

    public MethodDecl(int line, int column, String id, ASTType returnType, List<Param> params, Block body) {
        super(line, column);
        this.id = id;
        this.returnType = returnType;
        this.params = params;
        this.body = body;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitMethodDecl(this);
    }
}