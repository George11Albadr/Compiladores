package compiler.ast;

import compiler.ast.*; // Importa todas las clases en el paquete `compiler.ast`

public class ASTPrinter implements ASTVisitor {
    private int indent = 0;

    // Método auxiliar para manejar la indentación en la salida
    private void printIndent() {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }

    // Método para imprimir los nodos con indentación
    private void printNode(String nodeName) {
        printIndent();
        System.out.println(nodeName);
    }

    // Nodo Program
    @Override
    public void visitProgram(Program program) {
        printNode("Program");
        indent++;
        for (ASTNode member : program.classMembers) { // Verificamos si `classMembers` es el nombre correcto
            member.accept(this);
        }
        indent--;
    }

    // Nodo AssignExpr
    @Override
    public void visitAssignExpr(AssignExpr assignExpr) {
        printNode("Assign Expression");
        indent++;
        assignExpr.var.accept(this);
        assignExpr.expr.accept(this);
        indent--;
    }

    // Nodo AssignStmt
    @Override
    public void visitAssignStmt(AssignStmt assignStmt) {
        printNode("Assign Statement");
        indent++;
        assignStmt.location.accept(this);
        assignStmt.expr.accept(this);
        indent--;
    }

    // Nodo BinOp
    @Override
    public void visitBinOp(BinOp binOp) {
        printNode("Binary Operation: " + binOp.op); // Cambiamos `operator` a `op` según el ejemplo
        indent++;
        binOp.left.accept(this);
        binOp.right.accept(this);
        indent--;
    }

    // Nodo Block
    @Override
    public void visitBlock(Block block) {
        printNode("Block");
        indent++;
        for (Statement stmt : block.statements) {
            stmt.accept(this);
        }
        indent--;
    }

    // Nodo BreakStmt
    @Override
    public void visitBreakStmt(BreakStmt breakStmt) {
        printNode("Break Statement");
    }

    // Nodo CalloutCall
    @Override
    public void visitCalloutCall(CalloutCall calloutCall) {
        printNode("Callout Call: " + calloutCall.methodName);
        indent++;
        for (Expression arg : calloutCall.args) {
            arg.accept(this);
        }
        indent--;
    }

    // Nodo ContinueStmt
    @Override
    public void visitContinueStmt(ContinueStmt continueStmt) {
        printNode("Continue Statement");
    }

    // Nodo Expression (abstracto)
    //@Override
    //public void visitExpression(Expression expr) {
    //    printNode("Expression");
    //} 

    // Nodo ForStmt
    @Override
    public void visitForStmt(ForStmt forStmt) {
        printNode("For Statement");
        indent++;
        forStmt.init.accept(this);
        forStmt.condition.accept(this);
        forStmt.update.accept(this);
        forStmt.body.accept(this);
        indent--;
    }

    // Nodo IfStmt
    @Override
    public void visitIfStmt(IfStmt ifStmt) {
        printNode("If Statement");
        indent++;
        ifStmt.condition.accept(this);
        ifStmt.thenBlock.accept(this);
        if (ifStmt.elseBlock != null) {
            printNode("Else Block");
            ifStmt.elseBlock.accept(this);
        }
        indent--;
    }

    // Nodo Literal
    @Override
    public void visitLiteral(Literal literal) {
        printNode("Literal: " + literal.value); // Asegúrate de que `value` es el nombre correcto del campo
    }

    // Nodo MethodCall
    @Override
    public void visitMethodCall(MethodCall methodCall) {
        printNode("Method Call: " + methodCall.methodName);
        indent++;
        for (Expression arg : methodCall.args) {
            arg.accept(this);
        }
        indent--;
    }

    // Nodo MethodCallStmt
    @Override
    public void visitMethodCallStmt(MethodCallStmt methodCallStmt) {
        printNode("Method Call Statement");
        methodCallStmt.call.accept(this);
    }

    // Nodo MethodDecl
    @Override
    public void visitMethodDecl(MethodDecl methodDecl) {
        printNode("Method Declaration: " + methodDecl.id);
        indent++;
        for (Param param : methodDecl.params) {
            param.accept(this);
        }
        methodDecl.body.accept(this);
        indent--;
    }

    // Nodo NewArray
    @Override
    public void visitNewArray(NewArray newArray) {
        printNode("New Array of type: " + newArray.type);
        indent++;
        newArray.size.accept(this);
        indent--;
    }

    // Nodo NewObject
    @Override
    public void visitNewObject(NewObject newObject) {
        printNode("New Object: " + newObject.className);
    }

    // Nodo Param
    @Override
    public void visitParam(Param param) {
        printNode("Parameter: " + param.id + " of type " + param.type);
    }

    // Nodo ReturnStmt
    @Override
    public void visitReturnStmt(ReturnStmt returnStmt) {
        printNode("Return Statement");
        indent++;
        if (returnStmt.expr != null) {
            returnStmt.expr.accept(this);
        }
        indent--;
    }

    // Nodo Statement (abstracto)
    @Override
    public void visitStatement(Statement stmt) {
        printNode("Statement");
    }

    // Nodo Type
    @Override
    public void visitASTType(ASTType type) {
        printNode("Type: " + type.typeName);
    }

    // Nodo UnaryOp
    @Override
    public void visitUnaryOp(UnaryOp unaryOp) {
        printNode("Unary Operation: " + unaryOp.op); // Cambiamos `operator` a `op`
        indent++;
        unaryOp.expr.accept(this);
        indent--;
    }

    // Nodo VarDecl
    @Override
    public void visitVarDecl(VarDecl varDecl) {
        printNode("Variable Declaration: " + varDecl.id + " of type " + varDecl.type);
        indent++;
        if (varDecl.initExpr != null) {
            varDecl.initExpr.accept(this);
        }
        indent--;
    }

    // Nodo VarLocation
    @Override
    public void visitVarLocation(VarLocation varLocation) {
        printNode("Variable Location: " + varLocation.id);
    }

    // Nodo WhileStmt
    @Override
    public void visitWhileStmt(WhileStmt whileStmt) {
        printNode("While Statement");
        indent++;
        whileStmt.condition.accept(this);
        whileStmt.body.accept(this);
        indent--;
    }
}
