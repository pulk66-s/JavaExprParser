package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.OperationExpression;

public class SubstractionExpression extends OperationExpression {
    public SubstractionExpression() {
        this.operator = '-';
    }

    @Override
    public Double evaluate() {
        return this.left.evaluate() - this.right.evaluate();
    }
}
