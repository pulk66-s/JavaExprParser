package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;

public class SubstractionExpression extends OperationExpression {
    public SubstractionExpression() {
        this.operator = '-';
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() - this.right.evaluate();
    }
}
