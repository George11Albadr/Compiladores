// Archivo: compiler/irt/IRDotGenerator.java
package compiler.irt;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que genera una representación en formato DOT del IRT.
 */
public class IRDotGenerator implements IRTVisitor {
    private PrintWriter writer;
    private Map<Object, Integer> nodeIds;
    private int currentId;

    /**
     * Constructor para IRDotGenerator.
     *
     * @param writer El PrintWriter donde se escribirá la salida DOT.
     */
    public IRDotGenerator(PrintWriter writer) {
        this.writer = writer;
        this.nodeIds = new HashMap<>();
        this.currentId = 0;
    }

    /**
     * Genera el archivo DOT a partir del nodo raíz del IRT.
     *
     * @param root El nodo raíz del IRT.
     */
    public void generateDot(IRNode root) {
        if (root == null) {
            throw new IllegalArgumentException("El nodo raíz no puede ser nulo.");
        }
        writer.println("digraph IRT {");
        writer.println("    node [shape=box];");
        root.accept(this);
        writer.println("}");
        writer.flush();
    }

    /**
     * Obtiene o asigna un ID único para un nodo.
     *
     * @param node El nodo del IRT.
     * @return El ID único asignado al nodo.
     */
    private int getNodeId(Object node) {
        if (node == null) {
            throw new IllegalArgumentException("El nodo no puede ser nulo.");
        }
        nodeIds.putIfAbsent(node, currentId++);
        return nodeIds.get(node);
    }

    @Override
    public void visit(BasicBlock node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"" + node.getLabel() + "\"];");
        for (IRStmt stmt : node.getStatements()) {
            if (stmt != null) {
                stmt.accept(this);
                writer.println("    node" + id + " -> node" + getNodeId(stmt) + ";");
            }
        }
        for (BasicBlock succ : node.getSuccessors()) {
            if (succ != null) {
                int succId = getNodeId(succ);
                writer.println("    node" + id + " -> node" + succId + " [style=dashed];");
            }
        }
    }

    @Override
    public void visit(BinOp node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"" + node.getOperator() + "\"];");
        if (node.getLeft() != null) {
            node.getLeft().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getLeft()) + ";");
        }
        if (node.getRight() != null) {
            node.getRight().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getRight()) + ";");
        }
    }

    @Override
    public void visit(CALL node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"CALL: " + node.getFunctionName() + "\"];");
        for (IRExp arg : node.getArguments()) {
            if (arg != null) {
                arg.accept(this);
                writer.println("    node" + id + " -> node" + getNodeId(arg) + ";");
            }
        }
        if (node.getReturnTemp() != null) {
            node.getReturnTemp().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getReturnTemp()) + " [label=\"return\"];");
        }
    }

    @Override
    public void visit(CJUMP node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"CJUMP: " + node.getConditionOperator() + "\"];");
        if (node.getLeft() != null) {
            node.getLeft().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getLeft()) + " [label=\"left\"];");
        }
        if (node.getRight() != null) {
            node.getRight().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getRight()) + " [label=\"right\"];");
        }
        writer.println("    node" + id + " -> node" + getNodeId(node.getTrueLabel()) + " [label=\"true\"];");
        writer.println("    node" + id + " -> node" + getNodeId(node.getFalseLabel()) + " [label=\"false\"];");
    }

    @Override
    public void visit(JUMP node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"JUMP\"];");
        if (node.getTarget() != null) {
            node.getTarget().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getTarget()) + ";");
        }
    }

    @Override
    public void visit(LABEL node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"LABEL: " + node.getName() + "\"];");
    }

    @Override
    public void visit(MOVE node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"MOVE\"];");
        if (node.getTarget() != null) {
            node.getTarget().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getTarget()) + " [label=\"target\"];");
        }
        if (node.getSource() != null) {
            node.getSource().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getSource()) + " [label=\"source\"];");
        }
    }

    @Override
    public void visit(RETURN node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"RETURN\"];");
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
            writer.println("    node" + id + " -> node" + getNodeId(node.getExpression()) + ";");
        }
    }

    @Override
    public void visit(TEMP node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"TEMP: " + node.getName() + "\"];");
    }

    @Override
    public void visit(IRResult node) {
        int id = getNodeId(node);
        writer.println("    node" + id + " [label=\"IRResult\"];");
    }
}