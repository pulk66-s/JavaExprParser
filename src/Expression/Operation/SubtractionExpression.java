package Expression.Operation;

import java.util.Optional;

import Expression.ArithmeticExpression;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

/**
 * @brief   This class is used to represent a substraction expression
 * @details This class is used to represent a substraction expression
 *          It extends the OperationExpression class
 */
public class SubtractionExpression extends OperationExpression {
    /**
     * @brief   This constructor initialize a Substraction with the left and right expression to 0.0
     */
    public SubtractionExpression() {
        this.operator = '-';
        this.unit = new NumberExpression(0.0);
        this.applyFunction = (Double a, Double b) -> a - b;
    }

    /**
     * @brief       This constructor initialize a Substraction with the left and right expression to the given value
     * @param left  The left expression
     * @param right The right expression
     */
    public SubtractionExpression(Optional<ArithmeticExpression> left, Optional<ArithmeticExpression> right) {
        this.operator = '-';
        this.unit = new NumberExpression(0.0);
        this.left = left;
        this.right = right;
        this.applyFunction = (Double a, Double b) -> a - b;
    }
}
