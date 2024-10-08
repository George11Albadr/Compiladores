package compiler;

import compiler.scanner.Scanner;
import compiler.parser.Parser;
import java_cup.runtime.Symbol;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import compiler.parser.sym;

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

        // Inicializar el PrintWriter fuera del try-with-resources
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(output));

            if (target.equals("scan")) {
                writer.println("stage: scanning");
                try (FileReader fileReader = new FileReader(filename)) {
                    Scanner scanner = new Scanner(fileReader); // Crear el escáner

                    while (true) { // Leer tokens hasta el EOF
                        Symbol token = scanner.next_token();
                        if (token.sym == sym.EOF) break;
                        writer.println("Token: " + sym.terminalNames[token.sym] + " (" + token.value + ") en la línea " + token.left + ", columna " + token.right);
                        if (debug) {
                            System.out.println("Debugging scan: Token -> " + sym.terminalNames[token.sym] + " (" + token.value + ")");
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo: " + e.getMessage());
                } catch (Exception e) {
                    writer.println("Error durante el escaneo: " + e.getMessage());
                    if (debug) {
                        e.printStackTrace(System.out);
                    }
                }
            } else if (target.equals("parse")) {
                writer.println("stage: parsing");
                try (FileReader fileReader = new FileReader(filename)) {
                    Scanner scanner = new Scanner(fileReader);
                    Parser parser = new Parser(scanner);
                    try {
                        parser.parse();
                        writer.println("Parsing completed successfully.");
                        if (debug) System.out.println("Debugging parse: Completed successfully");
                    } catch (RuntimeException e) {
                        // Captura la excepción lanzada por el scanner o parser
                        writer.println("Runtime Error: " + e.getMessage());
                        if (debug) e.printStackTrace(System.out);
                    } catch (Exception e) {
                        writer.println("Error during parsing: " + e.getMessage());
                        // Añadir esta línea para imprimir la traza de la excepción en el archivo de salida
                        e.printStackTrace(new PrintWriter(writer));
                        if (debug) {
                            System.out.println("Debugging parse: Error occurred");
                            e.printStackTrace(System.out);
                        }
                    }
                } catch (IOException e) {
                    writer.println("Error al leer el archivo: " + e.getMessage());
                }
            }
            // Otras fases como ast, semantic, irt, codegen se agregarán aquí
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de salida: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
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