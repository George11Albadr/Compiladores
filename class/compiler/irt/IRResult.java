// Archivo: compiler/irt/IRResult.java
package compiler.irt;

/**
 * Clase que representa el resultado de la generación del IRT.
 */
public class IRResult extends IRNode {
    private ControlFlowGraph cfg;

    /**
     * Constructor para IRResult.
     *
     * @param cfg El grafo de flujo de control generado.
     */
    public IRResult(ControlFlowGraph cfg) {
        this.cfg = cfg;
    }

    /**
     * Obtiene el grafo de flujo de control.
     *
     * @return El grafo de flujo de control.
     */
    public ControlFlowGraph getCfg() {
        return cfg;
    }

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará este nodo.
     */
    @Override
    public void accept(IRTVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Verifica la igualdad con otro objeto.
     *
     * @param obj El objeto a comparar.
     * @return Verdadero si son iguales, falso en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IRResult)) return false;
        IRResult other = (IRResult) obj;
        return this.cfg.equals(other.cfg);
    }

    /**
     * Retorna una representación en cadena del resultado del IRT.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return cfg.toString();
    }
}