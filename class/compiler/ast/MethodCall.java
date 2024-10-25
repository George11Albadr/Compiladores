package compiler.ast;

import java.util.List;

public class MethodCall extends Expression {
    public String methodName;
    public List<Expression> args;

    public MethodCall(int line, int column, String methodName, List<Expression> args) {
        super(line, column);
        this.methodName = methodName;
        this.args = args;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitMethodCall(this);
    }
}