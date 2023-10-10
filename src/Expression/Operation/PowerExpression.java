package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;

public class PowerExpression extends OperationExpression {
    public PowerExpression() {
        this.operator = '^';
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return Math.pow(this.left.evaluate(), this.right.evaluate());
    }
}
