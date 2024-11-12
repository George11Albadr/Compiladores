// En compiler/ast/NewArrayExpr.java
package compiler.ast;

public class NewArrayExpr extends Expression {
    private Type elementType;
    private Expression size;
    private Type type;

    public NewArrayExpr(Type type, Expression size) {
        this.type = elementType;
        this.size = size;
        this.type = new ArrayType(elementType);
    }

    public Type getElementType() {
        return elementType;
    }

    public Type getType() {
        return type;
    }

    public Expression getSize() {
        return size;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
