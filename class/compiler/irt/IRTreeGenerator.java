// Archivo: compiler/irt/IRTreeGenerator.java
package compiler.irt;

import compiler.ast.*;
import java.util.*;

/**
 * Clase que genera el Árbol de Representación Intermedia (IRT) y el grafo de flujo de control.
 */
public class IRTreeGenerator implements ASTVisitor {
    private ControlFlowGraph cfg;
    private BasicBlock currentBlock;
    private int labelCounter;
    private int tempCounter;
    private Stack<String> loopLabels;
    private String currentMethod;
    private List<IRStmt> irStatements;

    public IRTreeGenerator() {
        this.cfg = new ControlFlowGraph();
        this.labelCounter = 0;
        this.tempCounter = 0;
        this.loopLabels = new Stack<>();
        this.currentMethod = "";
        this.irStatements = new ArrayList<>();
    }

    private String generateLabel(String prefix) {
        return prefix + "_" + labelCounter++;
    }

    private String generateTemp() {
        return "t" + (tempCounter++);
    }

    public ControlFlowGraph getControlFlowGraph() {
        return cfg;
    }

    /**
     * Genera y optimiza el árbol de representación intermedia (IRT).
     *
     * @param program El programa AST para procesar.
     */
    public void generateAndOptimize(Program program) {
        if (program == null) {
            throw new IllegalArgumentException("El programa no puede ser nulo.");
        }
        visit(program);
        optimizeControlFlowGraph();
    }

    /**
     * Devuelve las instrucciones del IRT como una lista.
     *
     * @return Lista de instrucciones del IRT.
     */
    public List<IRStmt> getIRStatements() {
        return new ArrayList<>(irStatements);
    }

    /**
     * Aplica optimizaciones básicas al grafo de flujo de control.
     */
    private void optimizeControlFlowGraph() {
        for (BasicBlock block : cfg.getBlocks()) {
            if (block.isEmpty()) {
                cfg.removeBlock(block.getLabel());
            }
        }
    }

    @Override
    public void visit(Program program) {
        String entryLabel = generateLabel("entry");
        String exitLabel = generateLabel("exit");
        BasicBlock entryBlock = new BasicBlock(entryLabel);
        BasicBlock exitBlock = new BasicBlock(exitLabel);
        cfg.setEntry(entryBlock);
        cfg.setExit(exitBlock);
        currentBlock = entryBlock;

        for (ClassBodyMember member : program.getMembers()) {
            if (member != null) {
                member.accept(this);
            }
        }

        currentBlock.addStatement(new JUMP(new NAME(exitLabel)));
        currentBlock.addSuccessor(exitBlock);
    }

    @Override
    public void visit(MethodDecl methodDecl) {
        if (methodDecl == null) return;

        currentMethod = methodDecl.getName();
        String methodLabel = generateLabel(currentMethod);
        currentBlock.addStatement(new LABEL(methodLabel));
        BasicBlock methodBlock = new BasicBlock(methodLabel);
        cfg.addBlock(methodBlock);
        currentBlock = methodBlock;

        if (methodDecl.getBody() != null) {
            methodDecl.getBody().accept(this);
        }

        generateEpilogue();
        currentMethod = "";
    }

    @Override
    public void visit(Block block) {
        if (block == null) return;

        for (Statement stmt : block.getStatements()) {
            if (stmt != null) {
                stmt.accept(this);
            }
        }
    }

    @Override
    public void visit(AssignStmt assignStmt) {
        if (assignStmt == null) return;

        IRExp target = convertExpression(assignStmt.getLocation());
        IRExp source = convertExpression(assignStmt.getExpression());
        currentBlock.addStatement(new MOVE(target, source));
    }

    @Override
    public void visit(VarDeclStmt varDeclStmt) {
        if (varDeclStmt == null) return;

        varDeclStmt.getVarDecl().accept(this);
        if (varDeclStmt.getInitExpression() != null) {
            IRExp initValue = convertExpression(varDeclStmt.getInitExpression());
            TEMP varTemp = new TEMP(varDeclStmt.getVarDecl().getName());
            currentBlock.addStatement(new MOVE(varTemp, initValue));
        }
    }

    @Override
    public void visit(IfStmt ifStmt) {
        if (ifStmt == null) return;

        String thenLabel = generateLabel("then");
        String elseLabel = generateLabel("else");
        String endLabel = generateLabel("endif");

        IRExp condition = convertExpression(ifStmt.getCondition());
        currentBlock.addStatement(new CJUMP("!=", condition, new CONST(0), new NAME(thenLabel), new NAME(elseLabel)));

        BasicBlock thenBlock = new BasicBlock(thenLabel);
        cfg.addBlock(thenBlock);
        currentBlock.addSuccessor(thenBlock);
        currentBlock = thenBlock;
        if (ifStmt.getThenBlock() != null) {
            ifStmt.getThenBlock().accept(this);
        }
        currentBlock.addStatement(new JUMP(new NAME(endLabel)));

        BasicBlock afterThenBlock = new BasicBlock(endLabel);
        cfg.addBlock(afterThenBlock);

        BasicBlock elseBlock = new BasicBlock(elseLabel);
        cfg.addBlock(elseBlock);
        currentBlock = elseBlock;
        if (ifStmt.getElseBlock() != null) {
            ifStmt.getElseBlock().accept(this);
        }
        currentBlock.addStatement(new JUMP(new NAME(endLabel)));
        elseBlock.addSuccessor(afterThenBlock);

        currentBlock = afterThenBlock;
    }

    @Override
    public void visit(WhileStmt whileStmt) {
        if (whileStmt == null) return;

        String testLabel = generateLabel("while_test");
        String bodyLabel = generateLabel("while_body");
        String endLabel = generateLabel("while_end");

        currentBlock.addStatement(new JUMP(new NAME(testLabel)));
        BasicBlock testBlock = new BasicBlock(testLabel);
        cfg.addBlock(testBlock);
        currentBlock.addSuccessor(testBlock);
        currentBlock = testBlock;

        IRExp condition = convertExpression(whileStmt.getCondition());
        currentBlock.addStatement(new CJUMP("!=", condition, new CONST(0), new NAME(bodyLabel), new NAME(endLabel)));

        BasicBlock bodyBlock = new BasicBlock(bodyLabel);
        cfg.addBlock(bodyBlock);
        currentBlock.addSuccessor(bodyBlock);
        currentBlock = bodyBlock;
        loopLabels.push(endLabel);
        if (whileStmt.getBody() != null) {
            whileStmt.getBody().accept(this);
        }
        loopLabels.pop();
        currentBlock.addStatement(new JUMP(new NAME(testLabel)));

        BasicBlock afterWhileBlock = new BasicBlock(endLabel);
        cfg.addBlock(afterWhileBlock);
        currentBlock.addSuccessor(afterWhileBlock);
        currentBlock = afterWhileBlock;
    }

    @Override
    public void visit(ReturnStmt returnStmt) {
        if (returnStmt == null) return;

        if (returnStmt.getExpression() != null) {
            IRExp retExpr = convertExpression(returnStmt.getExpression());
            currentBlock.addStatement(new MOVE(new TEMP("return"), retExpr));
        }
        currentBlock.addStatement(new RETURN());
    }

    @Override
    public void visit(BreakStmt breakStmt) {
        if (breakStmt == null || loopLabels.isEmpty()) return;

        String endLabel = loopLabels.peek();
        currentBlock.addStatement(new JUMP(new NAME(endLabel)));
    }

    @Override
    public void visit(ContinueStmt continueStmt) {
        if (continueStmt == null || loopLabels.isEmpty()) return;

        String endLabel = loopLabels.peek();
        String testLabel = endLabel.replace("end", "test");
        currentBlock.addStatement(new JUMP(new NAME(testLabel)));
    }

    @Override
    public void visit(MethodCallStmt methodCallStmt) {
        if (methodCallStmt == null) return;

        MethodCall methodCall = methodCallStmt.getMethodCall();
        List<IRExp> args = new ArrayList<>();
        for (Expression arg : methodCall.getArguments()) {
            if (arg != null) {
                args.add(convertExpression(arg));
            }
        }
        CALL callStmt = new CALL(methodCall.getMethodName(), args, null);
        currentBlock.addStatement(callStmt);
    }

    @Override
    public void visit(ExprStmt exprStmt) {
        if (exprStmt == null) return;

        IRExp expr = convertExpression(exprStmt.getExpression());
        currentBlock.addStatement(new EXPR(expr));
    }

    @Override
    public void visit(CalloutStmt calloutStmt) {
        if (calloutStmt == null) return;

        List<IRExp> args = new ArrayList<>();
        for (CalloutArg arg : calloutStmt.getArgs()) {
            if (arg instanceof ExprArg) {
                args.add(convertExpression(((ExprArg) arg).getExpression()));
            } else if (arg instanceof StringArg) {
                args.add(new NAME(((StringArg) arg).getValue()));
            }
        }
        CALL callStmt = new CALL(calloutStmt.getFunctionName(), args, null);
        currentBlock.addStatement(callStmt);
    }

    private IRExp convertExpression(Expression expr) {
        if (expr instanceof IntLiteral) {
            return new CONST(((IntLiteral) expr).getValue());
        } else if (expr instanceof BoolLiteral) {
            return new CONST(((BoolLiteral) expr).getValue() ? 1 : 0);
        } else if (expr instanceof VarLocation) {
            return new TEMP(((VarLocation) expr).getName());
        } else if (expr instanceof BinaryExpr) {
            BinaryExpr binExpr = (BinaryExpr) expr;
            IRExp left = convertExpression(binExpr.getLeft());
            IRExp right = convertExpression(binExpr.getRight());
            return new BinOp(translateOperator(binExpr.getOperator()), left, right);
        } else if (expr instanceof UnaryExpr) {
            UnaryExpr unaryExpr = (UnaryExpr) expr;
            IRExp operand = convertExpression(unaryExpr.getExpression());
            return new BinOp(translateOperator(unaryExpr.getOperator()), operand, null);
        }
        return null;
    }

    private String translateOperator(String op) {
        switch (op) {
            case "+":
                return "ADD";
            case "-":
                return "SUB";
            case "*":
                return "MUL";
            case "/":
                return "DIV";
            case "&&":
                return "AND";
            case "||":
                return "OR";
            case "<":
                return "LT";
            case "<=":
                return "LE";
            case ">":
                return "GT";
            case ">=":
                return "GE";
            case "==":
                return "EQ";
            case "!=":
                return "NE";
            default:
                throw new UnsupportedOperationException("Operador no soportado: " + op);
        }
    }

    private void generateEpilogue() {
        irStatements.add(new RETURN());
    }

    @Override
    public void visit(NullType node) {
        // Implementación vacía para manejar nodos NullType.
    }
}