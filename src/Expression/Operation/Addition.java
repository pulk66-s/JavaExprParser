package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.OperationExpression;

public class Addition extends OperationExpression {
    public Addition() {
        this.operator = '+';
    }

    @Override
    public Double evaluate() {
        return this.left.evaluate() + this.right.evaluate();
    }
}
