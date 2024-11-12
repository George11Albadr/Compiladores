package compiler.ast;

public class StringType extends Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}