package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Expression.OperationExpression;

public class Addition extends OperationExpression {
    private int left, right;
    private char operator;

    public Addition() {
    }

    public StatusCode evaluate(Environnement env) {
        int opIndex = env.findChar('+');

        if (opIndex < 0) {
            return StatusCode.FAILURE;
        }
        this.left = env.findNumber(opIndex, false);
        this.right = env.findNumber(opIndex, true);
        return StatusCode.SUCCESS;
    }

    public String toString() {
        return "Addition (" + this.left + "+" + this.right + ")";
    }
}
