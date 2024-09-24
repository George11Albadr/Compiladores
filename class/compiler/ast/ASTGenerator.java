import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class ASTGenerator {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ASTGenerator <Parser.cup path> <output directory>");
            return;
        }

        String parserCupPath = args[0];
        String outputDir = args[1];

        try {
            String content = new String(Files.readAllBytes(Paths.get(parserCupPath)));
            List<String> classes = extractClasses(content);

            for (String className : classes) {
                generateClass(className, outputDir);
            }

            System.out.println("AST classes generated successfully.");
        } catch (IOException e) {
            System.err.println("Error reading Parser.cup file: " + e.getMessage());
        }
    }

    private static List<String> extractClasses(String content) {
        List<String> classes = new ArrayList<>();
        Pattern pattern = Pattern.compile("non\\s+terminal\\s+(\\w+)\\s+(\\w+)");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            classes.add(matcher.group(2));
        }

        return classes;
    }

    private static void generateClass(String className, String outputDir) throws IOException {
        String template = 
            "package compiler.ast;\n\n" +
            "public class " + className + " {\n" +
            "    // TODO: Add fields and methods\n" +
            "}\n";

        File file = new File(outputDir, className + ".java");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(template);
        }
    }
}