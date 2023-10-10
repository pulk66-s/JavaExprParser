package Expression.Operation;

import Expression.OperationExpression;

public class MultiplicationExpression extends OperationExpression {
    public MultiplicationExpression() {
        this.operator = '*';
    }

    @Override
    public Double evaluate() {
        return this.left.evaluate() * this.right.evaluate();
    }
}
