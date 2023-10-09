package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Expression.ArithmeticExpression;
import Expression.OperationExpression;

public class Addition extends OperationExpression {
    private ArithmeticExpression left, right;
    private char operator;

    public Addition() {
    }

    public StatusCode evaluate(Environnement env) {
        return StatusCode.SUCCESS;
    }
}
