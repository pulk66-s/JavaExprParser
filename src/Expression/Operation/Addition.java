package Expression.Operation;

import Exception.VariableNotExistError;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

public class Addition extends OperationExpression {
    public Addition() {
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
    }

    @Override
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() + this.right.evaluate();
    }
}
