// Archivo: compiler/irt/RETURN.java
package compiler.irt;

/**
 * Clase que representa una sentencia de retorno en el IRT.
 */
public class RETURN extends IRStmt {
    private IRExp expression;

    /**
     * Constructor para RETURN con expresión.
     *
     * @param expression La expresión que se retorna. Puede ser null si no se retorna nada.
     */
    public RETURN(IRExp expression) {
        this.expression = (expression != null) ? expression : null;
    }

    /**
     * Constructor sin expresión de retorno.
     */
    public RETURN() {
        this.expression = null;
    }

    /**
     * Obtiene la expresión retornada.
     *
     * @return La expresión retornada, o null si no hay expresión.
     */
    public IRExp getExpression() {
        return expression;
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
        if (!(obj instanceof RETURN)) return false;
        RETURN other = (RETURN) obj;
        if (this.expression == null && other.expression == null) {
            return true;
        }
        if (this.expression != null) {
            return this.expression.equals(other.expression);
        }
        return false;
    }

    /**
     * Retorna una representación en cadena de la sentencia de retorno.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        if (expression != null) {
            return "RETURN(" + expression.toString() + ")";
        }
        return "RETURN";
    }
}