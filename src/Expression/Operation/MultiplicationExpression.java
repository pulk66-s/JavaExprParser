package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;

public class MultiplicationExpression extends OperationExpression {
    public MultiplicationExpression() {
        this.operator = '*';
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() * this.right.evaluate();
    }
}
