// Archivo: compiler/irt/TEMP.java
package compiler.irt;

/**
 * Clase que representa un temporal en el IRT.
 */
public class TEMP extends IRExp {
    private String name;

    /**
     * Constructor para TEMP.
     *
     * @param name El nombre del temporal.
     */
    public TEMP(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre del temporal.
     *
     * @return El nombre del temporal.
     */
    public String getName() {
        return name;
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
        if (!(obj instanceof TEMP)) return false;
        TEMP other = (TEMP) obj;
        return this.name.equals(other.name);
    }

    /**
     * Retorna una representación en cadena del temporal.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "TEMP(" + name + ")";
    }
}