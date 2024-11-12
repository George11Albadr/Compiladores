package compiler.ast;

public class CharLiteral extends Literal {
    public char value;

    public CharLiteral(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
