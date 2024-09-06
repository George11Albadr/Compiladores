import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Lexer {
    // Definimos tipos de tokens
    private static final int TOKEN_ARITH_OP = 1;
    private static final int TOKEN_DIGIT = 2;
    private static final int TOKEN_ALPHA = 3;
    private static final int TOKEN_ID = 4;
    private static final int TOKEN_ASSIGN = 5;    // Para '='
    private static final int TOKEN_SEMICOLON = 6; // Para ';'
    private static final int TOKEN_LPAREN = 7;     // Para '('
    private static final int TOKEN_RPAREN = 8;     // Para ')'
    private static final int TOKEN_LT = 9;         // Para '<'
    private static final int TOKEN_GT = 10;        // Para '>'
    private static final int TOKEN_LE = 11;        // Para '<='
    private static final int TOKEN_GE = 12;        // Para '>='
    private static final int TOKEN_EQ = 13;        // Para '=='
    private static final int TOKEN_NE = 14;        // Para '!='
    private static final int TOKEN_UNKNOWN = -1;

    private BufferedReader reader;
    private int line = 1;
    private int column = 0;
    private int currentChar;

    public Lexer(String filePath) throws IOException {
        reader = new BufferedReader(new FileReader(filePath));
        advance();
    }

    // Avanza al siguiente carácter
    private void advance() throws IOException {
        currentChar = reader.read();
        column++;
    }

    // Procesa el archivo y genera una lista de tokens
    public List<Token> tokenize() throws IOException {
        List<Token> tokens = new LinkedList<>();

        while (currentChar != -1) {
            char ch = (char) currentChar;

            // Manejo de espacios en blanco
            if (Character.isWhitespace(ch)) {
                if (ch == '\n') {
                    line++;
                    column = 0;
                }
                advance();
                continue;
            }

            // Manejo de operadores aritméticos
            if (isArithOp(ch)) {
                tokens.add(new Token(TOKEN_ARITH_OP, String.valueOf(ch), line, column));
                advance();
                continue;
            }

            // Manejo de dígitos
            if (Character.isDigit(ch)) {
                tokens.add(new Token(TOKEN_DIGIT, String.valueOf(ch), line, column));
                advance();
                continue;
            }

            // Manejo de letras (para identificadores)
            if (Character.isLetter(ch)) {
                StringBuilder idBuilder = new StringBuilder();
                int startColumn = column;

                while (Character.isLetterOrDigit(ch)) {
                    idBuilder.append(ch);
                    advance();
                    ch = (char) currentChar;
                }

                tokens.add(new Token(TOKEN_ID, idBuilder.toString(), line, startColumn));
                continue;
            }

            // Manejo de operadores relacionales y de igualdad
            if (ch == '=') {
                advance();
                if (currentChar == '=') {
                    tokens.add(new Token(TOKEN_EQ, "==", line, column));
                    advance();
                } else {
                    tokens.add(new Token(TOKEN_ASSIGN, "=", line, column));
                }
                continue;
            }

            if (ch == '<') {
                advance();
                if (currentChar == '=') {
                    tokens.add(new Token(TOKEN_LE, "<=", line, column));
                    advance();
                } else {
                    tokens.add(new Token(TOKEN_LT, "<", line, column));
                }
                continue;
            }

            if (ch == '>') {
                advance();
                if (currentChar == '=') {
                    tokens.add(new Token(TOKEN_GE, ">=", line, column));
                    advance();
                } else {
                    tokens.add(new Token(TOKEN_GT, ">", line, column));
                }
                continue;
            }

            if (ch == '!') {
                advance();
                if (currentChar == '=') {
                    tokens.add(new Token(TOKEN_NE, "!=", line, column));
                    advance();
                } else {
                    tokens.add(new Token(TOKEN_UNKNOWN, "!", line, column));
                }
                continue;
            }

            // Manejo de paréntesis '(' y ')'
            if (ch == '(') {
                tokens.add(new Token(TOKEN_LPAREN, String.valueOf(ch), line, column));
                advance();
                continue;
            }
            if (ch == ')') {
                tokens.add(new Token(TOKEN_RPAREN, String.valueOf(ch), line, column));
                advance();
                continue;
            }

            // Manejo del punto y coma ';'
            if (ch == ';') {
                tokens.add(new Token(TOKEN_SEMICOLON, String.valueOf(ch), line, column));
                advance();
                continue;
            }

            // Manejo de tokens desconocidos
            tokens.add(new Token(TOKEN_UNKNOWN, String.valueOf(ch), line, column));
            advance();
        }

        return tokens;
    }

    // Verifica si es un operador aritmético
    private boolean isArithOp(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }
}