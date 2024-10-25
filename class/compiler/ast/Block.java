package compiler.ast;

import java.util.List;

public class Block extends Statement {
    public List<Statement> statements;
    public List<VarDecl> varDecls;

    public Block(int line, int column, List<Statement> statements, List<VarDecl> varDecls) {
        super(line, column);
        this.statements = statements;
        this.varDecls = varDecls;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitBlock(this);
    }
}