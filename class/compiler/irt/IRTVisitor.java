// Archivo: compiler/irt/IRTVisitor.java
package compiler.irt;

/**
 * Interfaz para el patrón Visitor aplicado a los nodos del IRT.
 */
public interface IRTVisitor {

    // Métodos para visitar nodos de expresión
    void visit(CONST node);
    void visit(NAME node);
    void visit(TEMP node);
    void visit(BinOp node);
    void visit(MEM node);
    void visit(BinOpExpr node); // Agregado para soportar BinOpExpr.

    // Métodos para visitar nodos de sentencia
    void visit(MOVE node);
    void visit(CALL node);
    void visit(JUMP node);
    void visit(CJUMP node);
    void visit(RETURN node);
    void visit(SEQ node);
    void visit(LABEL node); // Agregado para soportar LABEL.

    // Métodos para bloques básicos y CFG
    void visit(BasicBlock node);
    void visit(ControlFlowGraph node);

    // Métodos para utilidades y generación de IR
    void visit(IRDotGenerator node);
    void visit(IRTreeGenerator node);
    void visit(IRTPrinter node);

    // Métodos adicionales para nodos específicos
    void visit(IRResult node);
}