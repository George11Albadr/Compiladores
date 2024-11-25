// Archivo: compiler/irt/NAME.java
package compiler.irt;

/**
 * Clase que representa un nombre en el IRT.
 */
public class NAME extends IRStmt {
    private String name;

    /**
     * Constructor para NAME.
     *
     * @param name El nombre representado por esta clase.
     */
    public NAME(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre.
     *
     * @return El nombre.
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
        if (!(obj instanceof NAME)) return false;
        NAME other = (NAME) obj;
        return this.name.equals(other.name);
    }

    /**
     * Retorna una representación en cadena del nombre.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return "NAME(" + name + ")";
    }
}