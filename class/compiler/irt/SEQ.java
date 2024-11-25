// Archivo: compiler/irt/SEQ.java
package compiler.irt;

import java.util.List;

/**
 * Clase que representa una secuencia de sentencias en el IRT.
 */
public class SEQ extends IRStmt {
    private List<IRStmt> statements;

    /**
     * Constructor para SEQ.
     *
     * @param statements La lista de sentencias que componen la secuencia.
     */
    public SEQ(List<IRStmt> statements) {
        this.statements = statements;
    }

    /**
     * Obtiene la lista de sentencias de la secuencia.
     *
     * @return La lista de sentencias.
     */
    public List<IRStmt> getStatements() {
        return statements;
    }

    /**
     * Acepta un visitante que implementa el patrón Visitor.
     *
     * @param visitor El visitante que procesará esta secuencia.
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
        if (!(obj instanceof SEQ)) return false;
        SEQ other = (SEQ) obj;
        return this.statements.equals(other.statements);
    }

    /**
     * Retorna una representación en cadena de la secuencia de sentencias.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SEQ(\n");
        for (IRStmt stmt : statements) {
            sb.append("  ").append(stmt.toString()).append("\n");
        }
        sb.append(")");
        return sb.toString();
    }
}