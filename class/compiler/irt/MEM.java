// Archivo: compiler/irt/MEM.java
package compiler.irt;

/**
 * Clase que representa un acceso a memoria en el IRT.
 */
public class MEM extends IRExp {
    private IRExp address;

    /**
     * Constructor para MEM.
     *
     * @param address La expresión que representa la dirección de memoria.
     */
    public MEM(IRExp address) {
        this.address = address;
    }

    /**
     * Obtiene la expresión de la dirección de memoria.
     *
     * @return La expresión de la dirección de memoria.
     */
    public IRExp getAddress() {
        return address;
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
        if (!(obj instanceof MEM)) return false;
        MEM other = (MEM) obj;
        return this.address.equals(other.address);
    }

    /**
     * Retorna una representación en cadena del acceso a memoria.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "MEM(" + address.toString() + ")";
    }
}