package compiler;

import compiler.scanner.Scanner;
import compiler.parser.Parser;
import compiler.parser.sym;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java_cup.runtime.Symbol; // Importar la clase Symbol de Java CUP

public class Compiler {
    public static void main(String[] args) {
        if (args.length < 1) {
            printHelp();
            System.exit(1);
        }

        String filename = "";
        String output = "output.txt";
        String target = "codegen"; // Por defecto
        boolean debug = false;

        // Procesamiento de argumentos
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    output = args[++i];
                    break;
                case "-target":
                    target = args[++i];
                    break;
                case "-debug":
                    debug = true;
                    break;
                case "-h":
                    printHelp();
                    System.exit(0);
                    break;
                default:
                    filename = args[i];
            }
        }

        // Validar que se haya especificado un archivo de entrada
        if (filename.isEmpty()) {
            System.err.println("Error: No se especificó un archivo de entrada.");
            printHelp();
            System.exit(1);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(output))) {
            if (target.equals("scan")) {
                writer.println("stage: scanning");
                try (FileReader fileReader = new FileReader(filename)) {
                    Scanner scanner = new Scanner(fileReader); // Crear el escáner

                    while (!scanner.yyatEOF()) { // Leer tokens hasta el EOF
                        Symbol token = scanner.next_token();
                        if (token.sym == sym.EOF) break;
                        writer.println("Token: " + token.value + " en la línea " + token.left + ", columna " + token.right);
                        if (debug) {
                            System.out.println("Debugging scan: Token -> " + token.value);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo: " + e.getMessage());
                }
                
            } else if (target.equals("parse")) {
                writer.println("stage: parsing");
                try (FileReader fileReader = new FileReader(filename)) {
                    Scanner scanner = new Scanner(fileReader);
                    Parser parser = new Parser(scanner);

                    try {
                        parser.parse(); // Ejecutar el análisis sintáctico
                        writer.println("Parsing completed successfully.");
                        if (debug) System.out.println("Debugging parse: Completed successfully");
                    } catch (Exception e) {
                        writer.println("Error during parsing: " + e.getMessage());
                        e.printStackTrace();
                        if (debug) System.out.println("Debugging parse: Error occurred");
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo: " + e.getMessage());
                }
            }
            // Otras fases como ast, semantic, irt, codegen se agregarán aquí
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de salida: " + e.getMessage());
        }
    }

    // Método para imprimir la ayuda del compilador
    private static void printHelp() {
        System.out.println("Uso: java Compiler [option] <filename>");
        System.out.println("-o <outname>: Especifica el nombre del archivo de salida.");
        System.out.println("-target <stage>: scan, parse, ast, semantic, irt, codegen.");
        System.out.println("-opt <opt_stage>: constant, algebraic.");
        System.out.println("-debug <stage>: Activa el modo debug.");
    }
}