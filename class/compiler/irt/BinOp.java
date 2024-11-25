// Archivo: compiler/irt/BinOp.java
package compiler.irt;

/**
 * Clase que representa una operación binaria en el IRT.
 */
public class BinOp extends IRExp {
    private String operator;
    private IRExp left;
    private IRExp right;

    /**
     * Constructor para BinOp.
     *
     * @param operator El operador binario (por ejemplo, "+", "-", "*", "/").
     * @param left     La expresión izquierda de la operación.
     * @param right    La expresión derecha de la operación.
     */
    public BinOp(String operator, IRExp left, IRExp right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    /**
     * Obtiene el operador binario.
     *
     * @return El operador binario.
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Obtiene la expresión izquierda.
     *
     * @return La expresión izquierda.
     */
    public IRExp getLeft() {
        return left;
    }

    /**
     * Obtiene la expresión derecha.
     *
     * @return La expresión derecha.
     */
    public IRExp getRight() {
        return right;
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
        if (!(obj instanceof BinOp)) return false;
        BinOp other = (BinOp) obj;
        return this.operator.equals(other.operator) &&
            this.left.equals(other.left) &&
            this.right.equals(other.right);
    }

    /**
     * Retorna una representación en cadena de la operación binaria.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
    }
}