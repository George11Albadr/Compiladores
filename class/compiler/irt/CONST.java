// Archivo: compiler/irt/CONST.java
package compiler.irt;

/**
 * Clase que representa una constante en el IRT.
 */
public class CONST extends IRExp {
    private int value;

    /**
     * Constructor para CONST.
     *
     * @param value El valor de la constante.
     */
    public CONST(int value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la constante.
     *
     * @return El valor de la constante.
     */
    public int getValue() {
        return value;
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
        if (!(obj instanceof CONST)) return false;
        CONST other = (CONST) obj;
        return this.value == other.value;
    }

    /**
     * Retorna una representación en cadena de la constante.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}