package compiler;

import compiler.scanner.Scanner;
import compiler.parser.Parser;
import java_cup.runtime.Symbol;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import compiler.parser.sym;
import compiler.ast.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.BufferedWriter;

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

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(output));

            if (target.equals("scan")) {
                writer.println("stage: scanning");
                try (FileReader fileReader = new FileReader(filename)) {
                    Scanner scanner = new Scanner(fileReader);

                    while (true) {
                        Symbol token = scanner.next_token();
                        if (token.sym == sym.EOF) break;
                        // Token detection
                        writer.println("Token: " + sym.terminalNames[token.sym] + " (" + token.value + ") en la línea " + (token.left + 1) + ", columna " + (token.right + 1));

                        // Debugging
                        if (debug) {
                            System.out.println("Debugging scan: Token -> " + sym.terminalNames[token.sym] + " (" + token.value + ") at line " + (token.left + 1) + ", column " + (token.right + 1));
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
                        Program ast = (Program) parser.parse().value;
                        writer.println("Parsing completed successfully.");
                        if (debug) System.out.println("Debugging parse: Completed successfully");


                        if (ast != null) {
                            ast.accept(new ASTPrinter());
                        } else { 
                            System.out.println("Error: el valor ast es nulo");
                        }

                        // Imprimir el AST
                        printAST(ast, "", writer);

                        // Generar el archivo DOT para visualizar el AST
                        generateDot(ast, "ast.dot");
                        writer.println("Generated AST in ast.dot");

                    } catch (RuntimeException e) {
                        writer.println("Runtime Error: " + e.getMessage());
                        if (debug) {
                            System.out.println("Debugging runtime error: " + e.getMessage());
                            e.printStackTrace(System.out);
                        }
                    } catch (Exception e) {
                        writer.println("Error durante el parsing: " + e.getMessage());
                        if (debug) {
                            System.out.println("Debugging parse exception: " + e.getMessage());
                            e.printStackTrace(System.out);
                        }
                        e.printStackTrace(new PrintWriter(writer));
                    }
                } catch (IOException e) {
                    writer.println("Error al leer el archivo: " + e.getMessage());
                }
            }
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
        System.out.println("-debug: Activa el modo debug para más detalles.");
    }

    // Método para imprimir el AST
    public static void printAST(ASTNode node, String indent, PrintWriter writer) {
        if (node instanceof Program) {
            Program program = (Program) node;
            writer.println(indent + "Program: " + program.className);
            for (ASTNode member : program.classMembers) {
                printAST(member, indent + "  ", writer);
            }
        } else if (node instanceof VarDecl) {
            VarDecl varDecl = (VarDecl) node;
            writer.println(indent + "VarDecl: " + varDecl.id + " Type: " + varDecl.type.typeName);
            if (varDecl.initExpr != null) {
                writer.println(indent + "  Init Expression:");
                printAST(varDecl.initExpr, indent + "    ", writer);
            }
        } else if (node instanceof MethodDecl) {
            MethodDecl methodDecl = (MethodDecl) node;
            writer.println(indent + "MethodDecl: " + methodDecl.id + " ReturnType: " + methodDecl.returnType.typeName);
            writer.println(indent + "  Parameters:");
            for (Param param : methodDecl.params) {
                printAST(param, indent + "    ", writer);
            }
            writer.println(indent + "  Body:");
            printAST(methodDecl.body, indent + "    ", writer);
        } else if (node instanceof Param) {
            Param param = (Param) node;
            writer.println(indent + "Param: " + param.id + " Type: " + param.type.typeName);
        } else if (node instanceof Block) {
            Block block = (Block) node;
            writer.println(indent + "Block:");
            if (!block.varDecls.isEmpty()) {
                writer.println(indent + "  VarDecls:");
                for (VarDecl varDecl : block.varDecls) {
                    printAST(varDecl, indent + "    ", writer);
                }
            }
            if (!block.statements.isEmpty()) {
                writer.println(indent + "  Statements:");
                for (Statement stmt : block.statements) {
                    printAST(stmt, indent + "    ", writer);
                }
            }
        } else if (node instanceof AssignStmt) {
            AssignStmt assignStmt = (AssignStmt) node;
            writer.println(indent + "AssignStmt:");
            writer.println(indent + "  Location:");
            printAST(assignStmt.location, indent + "    ", writer);
            writer.println(indent + "  Operator: " + assignStmt.operator);
            writer.println(indent + "  Expression:");
            printAST(assignStmt.expr, indent + "    ", writer);
        } else if (node instanceof IfStmt) {
            IfStmt ifStmt = (IfStmt) node;
            writer.println(indent + "IfStmt:");
            writer.println(indent + "  Condition:");
            printAST(ifStmt.condition, indent + "    ", writer);
            writer.println(indent + "  Then Block:");
            printAST(ifStmt.thenBlock, indent + "    ", writer);
            if (ifStmt.elseBlock != null) {
                writer.println(indent + "  Else Block:");
                printAST(ifStmt.elseBlock, indent + "    ", writer);
            }
        } else if (node instanceof VarLocation) {
            VarLocation varLoc = (VarLocation) node;
            writer.println(indent + "VarLocation: " + varLoc.id);
            if (varLoc.index != null) {
                writer.println(indent + "  Index:");
                printAST(varLoc.index, indent + "    ", writer);
            }
        } else if (node instanceof BinOp) {
            BinOp binOp = (BinOp) node;
            writer.println(indent + "BinOp(" + binOp.op + ")");
            writer.println(indent + "  Left:");
            printAST(binOp.left, indent + "    ", writer);
            writer.println(indent + "  Right:");
            printAST(binOp.right, indent + "    ", writer);
        } else if (node instanceof UnaryOp) {
            UnaryOp unaryOp = (UnaryOp) node;
            writer.println(indent + "UnaryOp(" + unaryOp.op + ")");
            writer.println(indent + "  Expression:");
            printAST(unaryOp.expr, indent + "    ", writer);
        } else if (node instanceof Literal) {
            Literal literal = (Literal) node;
            writer.println(indent + "Literal: " + literal.value);
        } else if (node instanceof MethodCallStmt) {
            MethodCallStmt methodCallStmt = (MethodCallStmt) node;
            writer.println(indent + "MethodCallStmt:");
            printAST(methodCallStmt.call, indent + "  ", writer);
        } else if (node instanceof MethodCall) {
            MethodCall methodCall = (MethodCall) node;
            writer.println(indent + "MethodCall: " + methodCall.methodName);
            if (!methodCall.args.isEmpty()) {
                writer.println(indent + "  Arguments:");
                for (Expression arg : methodCall.args) {
                    printAST(arg, indent + "    ", writer);
                }
            }
        } else if (node instanceof CalloutCall) {
            CalloutCall calloutCall = (CalloutCall) node;
            writer.println(indent + "CalloutCall: " + calloutCall.methodName);
            if (!calloutCall.args.isEmpty()) {
                writer.println(indent + "  Arguments:");
                for (Expression arg : calloutCall.args) {
                    printAST(arg, indent + "    ", writer);
                }
            }
        } else {
            writer.println(indent + "Unknown Node");
        }
    }

    // Método para generar el archivo DOT
    public static void generateDot(ASTNode node, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("digraph AST {\n");
        AtomicInteger nodeCounter = new AtomicInteger(0);
        generateDotNode(node, writer, nodeCounter);
        writer.write("}\n");
        writer.close();
    }

    public static int generateDotNode(ASTNode node, BufferedWriter writer, AtomicInteger nodeCounter) throws IOException {
        int myId = nodeCounter.getAndIncrement();
        String label = node.getClass().getSimpleName();

        if (node instanceof Program) {
            Program program = (Program) node;
            label += "\\n" + program.className;
        } else if (node instanceof VarDecl) {
            VarDecl varDecl = (VarDecl) node;
            label += "\\n" + varDecl.id + ": " + varDecl.type.typeName;
        } else if (node instanceof MethodDecl) {
            MethodDecl methodDecl = (MethodDecl) node;
            label += "\\n" + methodDecl.id + ": " + methodDecl.returnType.typeName;
        } else if (node instanceof Param) {
            Param param = (Param) node;
            label += "\\n" + param.id + ": " + param.type.typeName;
        } else if (node instanceof AssignStmt) {
            AssignStmt assignStmt = (AssignStmt) node;
            label += "\\n" + assignStmt.operator;
        } else if (node instanceof VarLocation) {
            VarLocation varLoc = (VarLocation) node;
            label += "\\n" + varLoc.id;
        } else if (node instanceof BinOp) {
            BinOp binOp = (BinOp) node;
            label += "\\n" + binOp.op;
        } else if (node instanceof UnaryOp) {
            UnaryOp unaryOp = (UnaryOp) node;
            label += "\\n" + unaryOp.op;
        } else if (node instanceof Literal) {
            Literal literal = (Literal) node;
            label += "\\n" + literal.value;
        } else if (node instanceof MethodCall) {
            MethodCall methodCall = (MethodCall) node;
            label += "\\n" + methodCall.methodName;
        }

        writer.write("  node" + myId + " [label=\"" + label + "\"];\n");

        if (node instanceof Program) {
            Program program = (Program) node;
            for (ASTNode member : program.classMembers) {
                int childId = generateDotNode(member, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + childId + ";\n");
            }
        } else if (node instanceof VarDecl) {
            VarDecl varDecl = (VarDecl) node;
            if (varDecl.initExpr != null) {
                int childId = generateDotNode(varDecl.initExpr, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + childId + ";\n");
            }
        } else if (node instanceof MethodDecl) {
            MethodDecl methodDecl = (MethodDecl) node;
            for (Param param : methodDecl.params) {
                int childId = generateDotNode(param, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + childId + ";\n");
            }
            int bodyId = generateDotNode(methodDecl.body, writer, nodeCounter);
            writer.write("  node" + myId + " -> node" + bodyId + ";\n");
        } else if (node instanceof Block) {
            Block block = (Block) node;
            for (VarDecl varDecl : block.varDecls) {
                int childId = generateDotNode(varDecl, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + childId + ";\n");
            }
            for (Statement stmt : block.statements) {
                int childId = generateDotNode(stmt, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + childId + ";\n");
            }
        } else if (node instanceof AssignStmt) {
            AssignStmt assignStmt = (AssignStmt) node;
            int locId = generateDotNode(assignStmt.location, writer, nodeCounter);
            int exprId = generateDotNode(assignStmt.expr, writer, nodeCounter);
            writer.write("  node" + myId + " -> node" + locId + ";\n");
            writer.write("  node" + myId + " -> node" + exprId + ";\n");
        } else if (node instanceof IfStmt) {
            IfStmt ifStmt = (IfStmt) node;
            int condId = generateDotNode(ifStmt.condition, writer, nodeCounter);
            int thenId = generateDotNode(ifStmt.thenBlock, writer, nodeCounter);
            writer.write("  node" + myId + " -> node" + condId + ";\n");
            writer.write("  node" + myId + " -> node" + thenId + ";\n");
            if (ifStmt.elseBlock != null) {
                int elseId = generateDotNode(ifStmt.elseBlock, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + elseId + ";\n");
            }
        } else if (node instanceof VarLocation) {
            VarLocation varLoc = (VarLocation) node;
            if (varLoc.index != null) {
                int indexId = generateDotNode(varLoc.index, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + indexId + ";\n");
            }
        } else if (node instanceof BinOp) {
            BinOp binOp = (BinOp) node;
            int leftId = generateDotNode(binOp.left, writer, nodeCounter);
            int rightId = generateDotNode(binOp.right, writer, nodeCounter);
            writer.write("  node" + myId + " -> node" + leftId + ";\n");
            writer.write("  node" + myId + " -> node" + rightId + ";\n");
        } else if (node instanceof UnaryOp) {
            UnaryOp unaryOp = (UnaryOp) node;
            int exprId = generateDotNode(unaryOp.expr, writer, nodeCounter);
            writer.write("  node" + myId + " -> node" + exprId + ";\n");
        } else if (node instanceof MethodCall) {
            MethodCall methodCall = (MethodCall) node;
            for (Expression arg : methodCall.args) {
                int argId = generateDotNode(arg, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + argId + ";\n");
            }
        } else if (node instanceof CalloutCall) {
            CalloutCall calloutCall = (CalloutCall) node;
            for (Expression arg : calloutCall.args) {
                int argId = generateDotNode(arg, writer, nodeCounter);
                writer.write("  node" + myId + " -> node" + argId + ";\n");
            }
        }

        return myId;
    }
}