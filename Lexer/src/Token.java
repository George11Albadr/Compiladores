public class Token {
    int type;
    String value;
    int line;
    int column;

    public Token(int type, String value, int line, int column) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("Token[type=%d, value='%s', line=%d, column=%d]", type, value, line, column);
    }
}