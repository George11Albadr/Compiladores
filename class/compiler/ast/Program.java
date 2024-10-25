package compiler.ast;

import java.util.List;

public class Program extends ASTNode {
    public String className;
    public List<ASTNode> classMembers;

    public Program(int line, int column, String className, List<ASTNode> classMembers) {
        super(line, column);
        this.className = className;
        this.classMembers = classMembers;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visitProgram(this);
    }
}