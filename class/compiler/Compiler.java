package compiler;

import compiler.scanner.Scanner;
import compiler.parser.sym;
import compiler.parser.Parser;
import compiler.ast.Program;
import compiler.ast.ASTPrinter;
import java_cup.runtime.Symbol;
import compiler.ast.ASTDotGenerator;
import compiler.semantic.SemanticAnalyzer;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.List;

public class Compiler {
    public static void main(String[] args) {
        if (args.length < 1) {
            printHelp();
            System.exit(1);
        }

        String filename = "";
        String output = "output.txt";
        String target = "parse"; // Cambiado el valor por defecto a "parse"
        boolean debug = false;

        // Procesamiento de argumentos
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o":
                    if (i + 1 < args.length) {
                        output = args[++i];
                    } else {
                        System.err.println("Error: Se espera un nombre de archivo después de -o.");
                        printHelp();
                        System.exit(1);
                    }
                    break;
                case "-target":
                    if (i + 1 < args.length) {
                        target = args[++i];
                    } else {
                        System.err.println("Error: Se espera un objetivo después de -target.");
                        printHelp();
                        System.exit(1);
                    }
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

        if (filename.isEmpty()) {
            System.err.println("Error: No se especificó un archivo de entrada.");
            printHelp();
            System.exit(1);
        }

        try {
            switch (target) {
                case "scan":
                    runScan(filename, output, debug);
                    break;
                case "parse":
                    runParse(filename, output, debug);
                    break;
                case "semantic":
                    runSemantic(filename, output, debug);
                    break;
                case "dot":
                    runDot(filename, output, debug);
                    break;
                default:
                    System.err.println("Objetivo desconocido: " + target);
                    printHelp();
            }
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
            if (debug) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Error durante la compilación: " + e.getMessage());
            if (debug) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para ejecutar el análisis léxico (scanning).
     *
     * @param filename Archivo de entrada.
     * @param output   Archivo de salida.
     * @param debug    Bandera para activar el modo debug.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void runScan(String filename, String output, boolean debug) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(output))) {
            // Indicar el inicio de la etapa de scanning
            writer.println("stage: scanning");
            System.out.println("stage: scanning");

            try (FileReader fileReader = new FileReader(filename)) {
                Scanner scanner = new Scanner(fileReader);
                while (!scanner.yyatEOF()) {
                    Symbol token = scanner.next_token();
                    if (token.sym == sym.EOF) break;

                    String nombreToken = getTokenName(token.sym);
                    String valorToken = (token.value != null) ? token.value.toString() : "N/A";
                    String tipoToken = esReservada(token.sym) ? "reservada" : "no reservada";

                    writer.printf("Token: %s | Valor: %s | Línea: %d | Columna: %d | Tipo: %s%n",
                            nombreToken, valorToken, token.left + 1, token.right + 1, tipoToken);

                    if (debug) {
                        System.out.printf("Token: %s | Valor: %s | Línea: %d | Columna: %d | Tipo: %s%n",
                                nombreToken, valorToken, token.left + 1, token.right + 1, tipoToken);
                    }
                }
            } catch (Exception e) {
                writer.println("Error durante el análisis de escaneo: " + e.getMessage());
                System.err.println("Error durante el análisis de escaneo: " + e.getMessage());
                if (debug) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Método para ejecutar el análisis sintáctico y generar el AST.
     *
     * @param filename Archivo de entrada.
     * @param output   Archivo de salida.
     * @param debug    Bandera para activar el modo debug.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void runParse(String filename, String output, boolean debug) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(output))) {
            // Indicar el inicio de la etapa de parsing
            writer.println("stage: parsing");
            System.out.println("stage: parsing");
    
            // Inicializar Scanner y Parser
            Scanner scanner = new Scanner(new FileReader(filename));
            Parser parser = new Parser(scanner);
    
            // Pasar el PrintWriter al parser
            parser.setOutputWriter(writer);
    
            // Realizar el parsing
            Symbol result = parser.parse();
    
            // Verificar si el parsing resultó en un AST válido
            if (result == null || result.value == null) {
                writer.println("Error: No se pudo generar el AST.");
                if (debug) {
                    System.err.println("Error: No se pudo generar el AST.");
                }
                return;
            }
    
            // Obtener el nodo raíz del AST
            Program program = (Program) result.value;
    
            // Confirmar que el parsing fue exitoso
            writer.println("Parsing completed successfully.");
            if (debug) {
                System.out.println("Debug: Parsing completed successfully.");
            }
    
            // Indicar el inicio de la impresión del AST
            writer.println("AST:");
            ASTPrinter printer = new ASTPrinter(writer);
    
            // Recorrer el AST y generar la representación
            program.accept(printer);
            writer.println(); // Añadir una línea en blanco al final
    
            if (debug) {
                System.out.println("Debug: AST generado correctamente en " + output);
            }
    
            // Mensaje de éxito
            System.out.println("Parsing completado exitosamente. AST generado en " + output);
        } catch (Exception e) {
            System.err.println("Error durante el parsing: " + e.getMessage());
            if (debug) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para ejecutar el análisis semántico.
     *
     * @param filename Archivo de entrada.
     * @param output   Archivo de salida.
     * @param debug    Bandera para activar el modo debug.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void runSemantic(String filename, String output, boolean debug) throws IOException {
    try (PrintWriter writer = new PrintWriter(new FileWriter(output))) {
        // Indicar el inicio de la etapa de análisis semántico
        writer.println("stage: semantic analysis");
        System.out.println("stage: semantic analysis");

        // Inicializar Scanner y Parser
        Scanner scanner = new Scanner(new FileReader(filename));
        Parser parser = new Parser(scanner);

        // Pasar el PrintWriter al parser
        parser.setOutputWriter(writer);

        // Realizar el parsing
        Symbol result = parser.parse();

        // Verificar si el parsing resultó en un AST válido
        if (result == null || result.value == null) {
            writer.println("Error: No se pudo generar el AST.");
            if (debug) {
                System.err.println("Error: No se pudo generar el AST.");
            }
            return;
        }

        // Obtener el nodo raíz del AST
        Program program = (Program) result.value;

        // **Iniciar el Análisis Semántico**
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();

        // Pasar el PrintWriter al analizador semántico
        semanticAnalyzer.setOutputWriter(writer);

        // Recorrer el AST con el analizador semántico
        program.accept(semanticAnalyzer);

        // Obtener los errores semánticos
        List<String> semanticErrors = semanticAnalyzer.getErrors();

        // **Reportar errores semánticos**
        if (!semanticErrors.isEmpty()) {
            writer.println("Semantic Errors:");
            System.out.println("Se encontraron errores semánticos:");
            for (String error : semanticErrors) {
                writer.println(error);
                System.out.println(error);
            }
        } else {
            writer.println("Semantic analysis completed successfully.");
            if (debug) {
                System.out.println("Debug: Semantic analysis completed successfully.");
            }
        }

        // Mensaje de éxito
        System.out.println("Análisis semántico completado. Resultado guardado en " + output);
    } catch (Exception e) {
        System.err.println("Error durante el análisis semántico: " + e.getMessage());
        if (debug) {
            e.printStackTrace();
        }
    }
}

    /**
     * Método para ejecutar la generación del archivo DOT y PDF.
     *
     * @param filename Archivo de entrada.
     * @param output   Archivo de salida.
     * @param debug    Bandera para activar el modo debug.
     * @throws IOException Si ocurre un error de E/S.
     */
    private static void runDot(String filename, String output, boolean debug) throws IOException {
        String dotFile = output;
        String pdfFile = output.replaceAll("\\.dot$", "") + ".pdf";

        try (PrintWriter writer = new PrintWriter(new FileWriter(dotFile))) {
            // Inicializar Scanner y Parser
            Scanner scanner = new Scanner(new FileReader(filename));
            Parser parser = new Parser(scanner);

            // Realizar el parsing
            Symbol result = parser.parse();

            if (result != null && result.value != null) {
                Program program = (Program) result.value;

                // Crear el generador DOT
                ASTDotGenerator dotGenerator = new ASTDotGenerator(writer);

                // Generar el archivo DOT
                dotGenerator.beginGraph();
                program.accept(dotGenerator);
                dotGenerator.endGraph();

                System.out.println("Archivo DOT generado exitosamente en " + dotFile);

                // Generar el PDF automáticamente
                try {
                    generatePDF(dotFile, pdfFile, debug);
                    System.out.println("PDF generado exitosamente en " + pdfFile);
                } catch (IOException e) {
                    System.err.println("Error generando PDF: " + e.getMessage());
                    if (debug) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error generando el archivo DOT/PDF: " + e.getMessage());
            if (debug) {
                e.printStackTrace();
            }
        }
    }

    /*Vamos a redirigir los mensajes de semantica a un archivo de texto*/
private static void redirectOutputToFile(String output) {
    try {
        // Crear un PrintStream que apunte al archivo
        PrintStream fileOut = new PrintStream(new FileOutputStream(output));
        
        // Redirigir System.out y System.err al archivo
        System.setOut(fileOut);
        System.setErr(fileOut);

        System.out.println("stage: semantic analysis");
    } catch (IOException e) {
        System.err.println("No se pudo redirigir la salida: " + e.getMessage());
    }
}

    /**
     * Método para generar el PDF a partir del archivo DOT utilizando Graphviz.
     *
     * @param dotFile Ruta al archivo DOT.
     * @param pdfFile Ruta al archivo PDF de salida.
     * @param debug   Bandera para activar el modo debug.
     * @throws IOException Si ocurre un error durante la ejecución de `dot`.
     */
    private static void generatePDF(String dotFile, String pdfFile, boolean debug) throws IOException {
        if (debug) {
            System.out.println("Contenido del archivo DOT antes de generar el PDF:");
            try (BufferedReader br = new BufferedReader(new FileReader(dotFile))) {
                String line;
                int currentLine = 1;
                while ((line = br.readLine()) != null) {
                    System.out.printf("%d: %s%n", currentLine, line);
                    currentLine++;
                }
            } catch (IOException e) {
                System.err.println("Error leyendo el archivo DOT para depuración: " + e.getMessage());
                if (debug) {
                    e.printStackTrace();
                }
            }
        }

        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpdf", dotFile, "-o", pdfFile);
        pb.redirectErrorStream(true); // Combina stdout y stderr

        // Ejecutar el proceso
        Process process = pb.start();

        // Leer la salida del proceso para detectar errores
        StringBuilder outputError = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                outputError.append(line).append("\n");
            }
        }

        try {
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException(outputError.toString().trim());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Proceso de generación de PDF interrumpido", e);
        }
    }

    /**
     * Método para determinar si un token es una palabra reservada.
     *
     * @param tokenSym Símbolo del token.
     * @return true si es reservada, false en caso contrario.
     */
    private static boolean esReservada(int tokenSym) {
        switch (tokenSym) {
            case sym.CLASS:
            case sym.INT:
            case sym.BOOLEAN:
            case sym.VOID:
            case sym.TRUE:
            case sym.FALSE:
            case sym.IF:
            case sym.ELSE:
            case sym.FOR:
            case sym.WHILE:
            case sym.RETURN:
            case sym.BREAK:
            case sym.CONTINUE:
            case sym.CALLOUT:
            case sym.CHAR:
            case sym.NEW:
            case sym.ASSIGN:
            case sym.PLUS_ASSIGN:
            case sym.MINUS_ASSIGN:
            case sym.SEMI:
            case sym.COMMA:
            case sym.LBRACE:
            case sym.RBRACE:
            case sym.LPAREN:
            case sym.RPAREN:
            case sym.LBRACKET:
            case sym.RBRACKET:
            case sym.AND:
            case sym.OR:
            case sym.NOT:
            case sym.EQ:
            case sym.NEQ:
            case sym.LE:
            case sym.GE:
            case sym.LT:
            case sym.GT:
            case sym.PLUS:
            case sym.MINUS:
            case sym.TIMES:
            case sym.DIVIDE:
            case sym.MOD:
                return true;
            default:
                return false;
        }
    }

    /**
     * Método para obtener el nombre de un token a partir de su símbolo.
     *
     * @param tokenSym Símbolo del token.
     * @return Nombre del token o "UNKNOWN" si no se encuentra.
     */
    private static String getTokenName(int tokenSym) {
        try {
            Field[] fields = sym.class.getFields();
            for (Field field : fields) {
                if (field.getType() == int.class && field.getInt(null) == tokenSym) {
                    return field.getName();
                }
            }
        } catch (IllegalAccessException e) {
            // Manejar excepción si es necesario
        }
        return "UNKNOWN";
    }

    /**
     * Método para imprimir la ayuda y uso del compilador.
     */
    private static void printHelp() {
        System.out.println("Uso: java compiler.Compiler [option] <filename>");
        System.out.println("-o <outname>: Especifica el nombre del archivo de salida.");
        System.out.println("-target <stage>: scan, parse, semantic, dot.");
        System.out.println("-debug: Activa el modo debug.");
        System.out.println("-h: Muestra esta ayuda.");
    }
}