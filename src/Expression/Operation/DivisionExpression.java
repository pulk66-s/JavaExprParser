package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;

public class DivisionExpression extends OperationExpression {
    public DivisionExpression() {
        this.operator = '/';
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() / this.right.evaluate();
    }
}
