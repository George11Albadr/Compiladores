package compiler.ast;

import java.util.List;
import java.util.ArrayList;

public class CalloutCall extends MethodCall {
    private String functionName;
    private List<CalloutArg> calloutArguments;

    public CalloutCall(String functionName, List<CalloutArg> calloutArguments) {
        super(functionName, new ArrayList<Expression>(calloutArguments));
        this.functionName = functionName;
        this.calloutArguments = calloutArguments;
    }

    
    public String getName() {
        return functionName;
    }

    
    public String getFunctionName() {
        return functionName;
    }

    
    public List<CalloutArg> getArgs() {
        return calloutArguments;
    }

    
    public List<CalloutArg> getCalloutArguments() {
        return calloutArguments;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}