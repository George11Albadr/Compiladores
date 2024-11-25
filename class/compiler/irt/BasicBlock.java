// Archivo: compiler/irt/BasicBlock.java
package compiler.irt;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un bloque básico en el IRT.
 */
public class BasicBlock {
    private String label;
    private List<IRStmt> statements;
    private List<BasicBlock> successors;

    /**
     * Constructor para BasicBlock.
     *
     * @param label La etiqueta del bloque.
     */
    public BasicBlock(String label) {
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("La etiqueta no puede ser nula o vacía.");
        }
        this.label = label;
        this.statements = new ArrayList<>();
        this.successors = new ArrayList<>();
    }

    /**
     * Obtiene la etiqueta del bloque.
     *
     * @return La etiqueta del bloque.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Obtiene las sentencias del bloque.
     *
     * @return La lista de sentencias.
     */
    public List<IRStmt> getStatements() {
        return statements;
    }

    /**
     * Añade una sentencia al bloque.
     *
     * @param stmt La sentencia a añadir.
     */
    public void addStatement(IRStmt stmt) {
        if (stmt == null) {
            throw new IllegalArgumentException("La sentencia no puede ser nula.");
        }
        statements.add(stmt);
    }

    /**
     * Verifica si el bloque no tiene sentencias.
     *
     * @return True si el bloque está vacío, false en caso contrario.
     */
    public boolean isEmpty() {
        return statements.isEmpty();
    }

    /**
     * Obtiene los bloques sucesores.
     *
     * @return La lista de bloques sucesores.
     */
    public List<BasicBlock> getSuccessors() {
        return successors;
    }

    /**
     * Añade un bloque sucesor.
     *
     * @param block El bloque sucesor a añadir.
     */
    public void addSuccessor(BasicBlock block) {
        if (block == null) {
            throw new IllegalArgumentException("El bloque sucesor no puede ser nulo.");
        }
        if (!successors.contains(block)) {
            successors.add(block);
        }
    }

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará este bloque.
     */
    public void accept(IRTVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Retorna una representación en cadena del bloque básico.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(label).append(":\n");
        if (statements.isEmpty()) {
            sb.append("  (No statements)\n");
        } else {
            for (IRStmt stmt : statements) {
                sb.append("  ").append(stmt.toString()).append("\n");
            }
        }
        sb.append("Successors: ");
        if (successors.isEmpty()) {
            sb.append("(No successors)");
        } else {
            for (BasicBlock succ : successors) {
                sb.append(succ.getLabel()).append(" ");
            }
        }
        return sb.toString().trim();
    }
}