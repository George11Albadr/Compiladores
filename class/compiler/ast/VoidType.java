package compiler.ast;

public class VoidType extends Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof VoidType;
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}