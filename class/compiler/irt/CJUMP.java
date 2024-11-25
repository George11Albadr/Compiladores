// Archivo: compiler/irt/CJUMP.java
package compiler.irt;

/**
 * Clase que representa una sentencia de salto condicional en el IRT.
 */
public class CJUMP extends IRStmt {
    private String conditionOperator;
    private IRExp left;
    private IRExp right;
    private String trueLabel;
    private String falseLabel;

    /**
     * Constructor para CJUMP.
     *
     * @param conditionOperator El operador de la condición (por ejemplo, "==", "!=", "<", etc.).
     * @param left               La expresión izquierda de la condición.
     * @param right              La expresión derecha de la condición.
     * @param trueLabel          El nombre de la etiqueta a la que salta si la condición es verdadera.
     * @param falseLabel         El nombre de la etiqueta a la que salta si la condición es falsa.
     */
    public CJUMP(String conditionOperator, IRExp left, IRExp right, String trueLabel, String falseLabel) {
        this.conditionOperator = conditionOperator;
        this.left = left;
        this.right = right;
        this.trueLabel = trueLabel;
        this.falseLabel = falseLabel;
    }

    /**
     * Obtiene el operador de la condición.
     *
     * @return El operador de la condición.
     */
    public String getConditionOperator() {
        return conditionOperator;
    }

    /**
     * Obtiene la expresión izquierda de la condición.
     *
     * @return La expresión izquierda.
     */
    public IRExp getLeft() {
        return left;
    }

    /**
     * Obtiene la expresión derecha de la condición.
     *
     * @return La expresión derecha.
     */
    public IRExp getRight() {
        return right;
    }

    /**
     * Obtiene el nombre de la etiqueta de salto verdadero.
     *
     * @return El nombre de la etiqueta de salto verdadero.
     */
    public String getTrueLabel() {
        return trueLabel;
    }

    /**
     * Obtiene el nombre de la etiqueta de salto falso.
     *
     * @return El nombre de la etiqueta de salto falso.
     */
    public String getFalseLabel() {
        return falseLabel;
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
        if (!(obj instanceof CJUMP)) return false;
        CJUMP other = (CJUMP) obj;
        return this.conditionOperator.equals(other.conditionOperator) &&
            this.left.equals(other.left) &&
            this.right.equals(other.right) &&
            this.trueLabel.equals(other.trueLabel) &&
            this.falseLabel.equals(other.falseLabel);
    }

    /**
     * Retorna una representación en cadena de la sentencia de salto condicional.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "CJUMP(" + left.toString() + " " + conditionOperator + " " + right.toString() +
            ", " + trueLabel + ", " + falseLabel + ")";
    }
}