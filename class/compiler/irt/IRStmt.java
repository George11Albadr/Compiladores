// Archivo: compiler/irt/IRStmt.java
package compiler.irt;

/**
 * Clase abstracta que representa una sentencia en el IRT.
 */
public abstract class IRStmt extends IRNode {

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará esta sentencia.
     */
    public abstract void accept(IRTVisitor visitor);
}