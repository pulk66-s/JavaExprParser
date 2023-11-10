package Expression.Operation;

import java.util.Optional;

import Expression.ArithmeticExpression;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

/**
 * @brief   This class is used to represent an addition expression
 * @details This class is used to represent an addition expression
 *          It extends the OperationExpression class
 */
public class Addition extends OperationExpression {
    /**
     * @brief   This constructor initialize an Addition with the left and right expression to 0.0
     */
    public Addition() {
        this.applyFunction = (Double a, Double b) -> a + b;
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
    }

    /**
     * @brief           This method parse an expression and return a StatusCode
     * @param   left    The left expression
     * @param   right   The right expression
     * @return          A boolean that represent the result of the evaluation
     */
    public Addition(ArithmeticExpression left, ArithmeticExpression right) {
        this.applyFunction = (Double a, Double b) -> a + b;
        this.left = Optional.of(left);
        this.right = Optional.of(right);
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
    }
}
