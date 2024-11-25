// Archivo: compiler/irt/LABEL.java
package compiler.irt;

/**
 * Clase que representa una etiqueta en el IRT.
 */
public class LABEL extends IRStmt {
    private String name;

    /**
     * Constructor para LABEL.
     *
     * @param name El nombre de la etiqueta.
     */
    public LABEL(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre de la etiqueta.
     *
     * @return El nombre de la etiqueta.
     */
    public String getName() {
        return name;
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
        if (!(obj instanceof LABEL)) return false;
        LABEL other = (LABEL) obj;
        return this.name.equals(other.name);
    }

    /**
     * Retorna una representación en cadena de la etiqueta.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "LABEL " + name;
    }
}