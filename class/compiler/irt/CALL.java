// Archivo: compiler/irt/CALL.java
package compiler.irt;

import java.util.List;

/**
 * Clase que representa una llamada a función en el IRT.
 */
public class CALL extends IRStmt {
    private String functionName;
    private List<IRExp> arguments;
    private TEMP returnTemp;

    /**
     * Constructor para CALL sin valor de retorno.
     *
     * @param functionName El nombre de la función a llamar.
     * @param arguments    La lista de argumentos de la función.
     */
    public CALL(String functionName, List<IRExp> arguments) {
        this(functionName, arguments, null);
    }

    /**
     * Constructor para CALL con valor de retorno.
     *
     * @param functionName El nombre de la función a llamar.
     * @param arguments    La lista de argumentos de la función.
     * @param returnTemp   El temporal donde se almacena el valor de retorno (opcional).
     */
    public CALL(String functionName, List<IRExp> arguments, TEMP returnTemp) {
        this.functionName = functionName;
        this.arguments = arguments;
        this.returnTemp = returnTemp;
    }

    /**
     * Obtiene el nombre de la función.
     *
     * @return El nombre de la función.
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Obtiene los argumentos de la función.
     *
     * @return La lista de argumentos.
     */
    public List<IRExp> getArguments() {
        return arguments;
    }

    /**
     * Obtiene el temporal del valor de retorno.
     *
     * @return El temporal del valor de retorno o null si no existe.
     */
    public TEMP getReturnTemp() {
        return returnTemp;
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
        if (!(obj instanceof CALL)) return false;
        CALL other = (CALL) obj;
        return this.functionName.equals(other.functionName) &&
               this.arguments.equals(other.arguments) &&
               ((this.returnTemp == null && other.returnTemp == null) ||
                (this.returnTemp != null && this.returnTemp.equals(other.returnTemp)));
    }

    /**
     * Retorna una representación en cadena de la llamada a función.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CALL(").append(functionName).append(", ");
        for (int i = 0; i < arguments.size(); i++) {
            sb.append(arguments.get(i).toString());
            if (i < arguments.size() - 1) {
                sb.append(", ");
            }
        }
        if (returnTemp != null) {
            sb.append(", Return: ").append(returnTemp.toString());
        }
        sb.append(")");
        return sb.toString();
    }
}