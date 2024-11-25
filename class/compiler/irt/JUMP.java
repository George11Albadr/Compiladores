// Archivo: compiler/irt/JUMP.java
package compiler.irt;

/**
 * Clase que representa una sentencia de salto incondicional en el IRT.
 */
public class JUMP extends IRStmt {
    private IRExp target;

    /**
     * Constructor para JUMP.
     *
     * @param target La expresión de destino a la que se realiza el salto.
     */
    public JUMP(IRExp target) {
        this.target = target;
    }

    /**
     * Obtiene la expresión de destino del salto.
     *
     * @return La expresión de destino.
     */
    public IRExp getTarget() {
        return target;
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
        if (!(obj instanceof JUMP)) return false;
        JUMP other = (JUMP) obj;
        return this.target.equals(other.target);
    }

    /**
     * Retorna una representación en cadena de la sentencia de salto incondicional.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "JUMP to " + target.toString();
    }
}