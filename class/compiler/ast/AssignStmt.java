package compiler.ast;

public class AssignStmt extends Statement {
    public VarLocation location;
    public String operator;
    public Expression expr;

    public AssignStmt(int line, int column, VarLocation location, String operator, Expression expr) {
        super(line, column);
        this.location = location;
        this.operator = operator;
        this.expr = expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitAssignStmt(this);
    }
}