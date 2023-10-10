package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;

public class Addition extends OperationExpression {
    public Addition() {
        this.operator = '+';
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() + this.right.evaluate();
    }
}
