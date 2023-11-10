package Expression.Operation;

import java.util.Optional;

import Expression.ArithmeticExpression;
import Expression.MinimalExpressionFactory;
import Expression.OperationExpression;

/**
 * @brief   This class is used to represent a multiplication expression
 * @details This class is used to represent a multiplication expression
 *          It extends the OperationExpression class
 */
public class MultiplicationExpression extends OperationExpression {
    /**
     * @brief   This constructor initialize a Multiplication with the left and right expression to 1.0
     */
    public MultiplicationExpression() {
        this.operator = '*';
        this.applyFunction = (Double a, Double b) -> a * b;
        this.unit = MinimalExpressionFactory.createConstant(1.0);
        this.nullValue = Optional.of(MinimalExpressionFactory.createConstant(0.0));
    }

    /**
     * @brief       This method is the constructor of the MultiplicationExpression class
     * @param left  The left expression
     * @param right The right expression
     */
    public MultiplicationExpression(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = Optional.of(left);
        this.right = Optional.of(right);
        this.applyFunction = (Double a, Double b) -> a * b;
        this.operator = '*';
        this.unit = MinimalExpressionFactory.createConstant(1.0);
        this.nullValue = Optional.of(MinimalExpressionFactory.createConstant(0.0));
    }
}
