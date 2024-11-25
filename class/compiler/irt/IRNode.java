// Archivo: compiler/irt/IRNode.java
package compiler.irt;

/**
 * Clase abstracta que representa un nodo en el IRT.
 */
public abstract class IRNode {

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará este nodo.
     */
    public abstract void accept(IRTVisitor visitor);
}