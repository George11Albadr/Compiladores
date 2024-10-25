package compiler.ast;

import compiler.ast.*;

public interface ASTVisitor {
    void visitProgram(Program program);
    void visitVarDecl(VarDecl varDecl);
    void visitMethodDecl(MethodDecl methodDecl);
    void visitAssignStmt(AssignStmt assignStmt);
    void visitAssignExpr(AssignExpr assignExpr);
    void visitVarLocation(VarLocation varLocation);
    void visitLiteral(Literal literal);
    void visitBinOp(BinOp binOp);
    void visitUnaryOp(UnaryOp unaryOp);
    void visitMethodCall(MethodCall methodCall);
    void visitMethodCallStmt(MethodCallStmt methodCallStmt);
    void visitBlock(Block block);
    void visitIfStmt(IfStmt ifStmt);
    void visitForStmt(ForStmt forStmt);
    void visitWhileStmt(WhileStmt whileStmt);
    void visitReturnStmt(ReturnStmt returnStmt);
    void visitBreakStmt(BreakStmt breakStmt);
    void visitContinueStmt(ContinueStmt continueStmt);
    void visitCalloutCall(CalloutCall calloutCall);
    void visitNewArray(NewArray newArray);
    void visitNewObject(NewObject newObject);
    void visitParam(Param param);
    void visitASTType(ASTType type);
    void visitStatement(Statement stmt);
}