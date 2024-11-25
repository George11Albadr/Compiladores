// Archivo: compiler/irt/IRExp.java
package compiler.irt;

/**
 * Clase abstracta que representa una expresión en el IRT.
 */
public abstract class IRExp extends IRNode {

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará este nodo.
     */
    public abstract void accept(IRTVisitor visitor);
}