// Archivo: compiler/irt/IRTPrinter.java
package compiler.irt;

import java.io.PrintWriter;
import java.util.List;

/**
 * Visitante que imprime los nodos del IRT.
 */
public class IRTPrinter implements IRTVisitor {
    private PrintWriter writer;

    /**
     * Constructor que acepta un PrintWriter para escribir la salida.
     *
     * @param writer El PrintWriter donde se escribirá la salida del IRT.
     */
    public IRTPrinter(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void visit(CONST node) {
        writer.print(node.getValue());
    }

    @Override
    public void visit(NAME node) {
        writer.print(node.getName());
    }

    @Override
    public void visit(TEMP node) {
        writer.print(node.getName());
    }

    @Override
    public void visit(BinOp node) {
        writer.print("(");
        node.getLeft().accept(this);
        writer.print(" " + node.getOperator() + " ");
        node.getRight().accept(this);
        writer.print(")");
    }

    @Override
    public void visit(MEM node) {
        writer.print("Mem[");
        if (node.getAddress() != null) {
            node.getAddress().accept(this);
        } else {
            writer.print("null");
        }
        writer.print("]");
    }

    @Override
    public void visit(JUMP node) {
        IRExp target = node.getTarget();
        if (target instanceof NAME) {
            NAME nameTarget = (NAME) target;
            writer.println("Jump to " + nameTarget.getName());
        } else {
            writer.println("Jump to unknown target");
        }
    }

    @Override
    public void visit(CJUMP node) {
        writer.print("If (");
        if (node.getLeft() != null) {
            node.getLeft().accept(this);
        } else {
            writer.print("null");
        }
        writer.print(" " + node.getConditionOperator() + " ");
        if (node.getRight() != null) {
            node.getRight().accept(this);
        } else {
            writer.print("null");
        }
        writer.print(") goto " + node.getTrueLabel());
        writer.println(" else goto " + node.getFalseLabel());
    }

    @Override
    public void visit(CALL node) {
        writer.print("Call " + node.getFunctionName() + "(");
        List<IRExp> args = node.getArguments();
        if (args != null && !args.isEmpty()) {
            for (int i = 0; i < args.size(); i++) {
                args.get(i).accept(this);
                if (i < args.size() - 1) {
                    writer.print(", ");
                }
            }
        }
        writer.println(")");
    }

    @Override
    public void visit(MOVE node) {
        writer.print("Move ");
        if (node.getTarget() != null) {
            node.getTarget().accept(this);
        } else {
            writer.print("null");
        }
        writer.print(", ");
        if (node.getSource() != null) {
            node.getSource().accept(this);
        } else {
            writer.print("null");
        }
        writer.println();
    }

    @Override
    public void visit(LABEL node) {
        writer.println(node.getName() + ":");
    }

    @Override
    public void visit(SEQ node) {
        for (IRStmt stmt : node.getStatements()) {
            if (stmt != null) {
                stmt.accept(this);
            }
        }
    }

    @Override
    public void visit(RETURN node) {
        writer.print("Return ");
        if (node.getExpression() != null) {
            node.getExpression().accept(this);
        }
        writer.println();
    }

    @Override
    public void visit(BasicBlock node) {
        writer.println("BasicBlock: " + node.getLabel());
        for (IRStmt stmt : node.getStatements()) {
            if (stmt != null) {
                stmt.accept(this);
            }
        }
        writer.println();
    }

    @Override
    public void visit(ControlFlowGraph node) {
        writer.println("ControlFlowGraph:");
        List<BasicBlock> blocks = node.getBlocks();
        if (blocks != null && !blocks.isEmpty()) {
            for (BasicBlock block : blocks) {
                if (block != null) {
                    block.accept(this);
                }
            }
        } else {
            writer.println("  (No blocks found)");
        }
    }

    @Override
    public void visit(IRResult node) {
        writer.println("IRResult: " + node.toString());
    }

    @Override
    public void visit(IRTPrinter node) {
        writer.println("Visiting IRTPrinter node");
    }
}