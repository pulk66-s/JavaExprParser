package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

public class PowerExpression extends OperationExpression {
    public PowerExpression() {
        this.operator = '^';
        this.unit = new NumberExpression(1.0);
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return Math.pow(this.left.evaluate(), this.right.evaluate());
    }
}
