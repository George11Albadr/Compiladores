import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Debe proporcionar la ruta del archivo de entrada.");
            System.exit(1);
        }

        try {
            Lexer lexer = new Lexer(args[0]);
            List<Token> tokens = lexer.tokenize();
            tokens.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}