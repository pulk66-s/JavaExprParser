package Expression.Operation;

import Expression.OperationExpression;

public class PowerExpression extends OperationExpression {
    public PowerExpression() {
        this.operator = '^';
    }

    @Override
    public Double evaluate() {
        return Math.pow(this.left.evaluate(), this.right.evaluate());
    }
}
