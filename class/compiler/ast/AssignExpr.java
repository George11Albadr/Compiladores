package compiler.ast;

public class AssignExpr extends Expression {
    public VarLocation var;  // Variable a asignar
    public Expression expr;  // Expresión asignada

    public AssignExpr(int line, int column, VarLocation var, Expression expr) {
        super(line, column);
        this.var = var;
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitAssignExpr(this);
    }
}
