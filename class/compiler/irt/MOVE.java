// Archivo: compiler/irt/MOVE.java
package compiler.irt;

/**
 * Clase que representa una sentencia de movimiento en el IRT.
 */
public class MOVE extends IRStmt {
    private IRExp target; // Cambiado de IRExpr a IRExp
    private IRExp source; // Cambiado de IRExpr a IRExp

    /**
     * Constructor para MOVE.
     *
     * @param target La expresión destino del movimiento.
     * @param source La expresión fuente del movimiento.
     */
    public MOVE(IRExp target, IRExp source) { // Cambiado de IRExpr a IRExp
        this.target = target;
        this.source = source;
    }

    /**
     * Obtiene la expresión destino.
     *
     * @return La expresión destino.
     */
    public IRExp getTarget() { // Cambiado de IRExpr a IRExp
        return target;
    }

    /**
     * Obtiene la expresión fuente.
     *
     * @return La expresión fuente.
     */
    public IRExp getSource() { // Cambiado de IRExpr a IRExp
        return source;
    }

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará esta sentencia.
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
        if (!(obj instanceof MOVE)) return false;
        MOVE other = (MOVE) obj;
        return this.target.equals(other.target) &&
            this.source.equals(other.source);
    }

    /**
     * Retorna una representación en cadena de la sentencia de movimiento.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "MOVE(" + target.toString() + ", " + source.toString() + ")";
    }
}