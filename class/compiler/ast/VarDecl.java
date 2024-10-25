package compiler.ast;

public class VarDecl extends Statement {
    public String id;
    public ASTType type;
    public Expression initExpr;

    public VarDecl(int line, int column, String id, ASTType type, Expression initExpr) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.initExpr = initExpr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitVarDecl(this);  // Asegúrate de que ASTVisitor tenga `visitVarDecl`
    }
}