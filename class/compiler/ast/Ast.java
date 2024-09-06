package compiler.ast;

public class Ast {
    public String type;
    public String value;

    public Ast(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Ast[type=" + type + ", value=" + value + "]";
    }
}