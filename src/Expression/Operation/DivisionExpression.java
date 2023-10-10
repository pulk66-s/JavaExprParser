package Expression.Operation;

import Expression.OperationExpression;

public class DivisionExpression extends OperationExpression {
    public DivisionExpression() {
        this.operator = '/';
    }

    @Override
    public Double evaluate() {
        return this.left.evaluate() / this.right.evaluate();
    }
}
