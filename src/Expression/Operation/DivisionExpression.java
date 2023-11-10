package Expression.Operation;

import java.util.Optional;

import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

/**
 * @brief   This class is used to represent a division expression
 * @details This class is used to represent a division expression
 *          It extends the OperationExpression class
 */
public class DivisionExpression extends OperationExpression {
    /**
     * @brief   This constructor initialize a Division with the left and right expression to 1.0
     */
    public DivisionExpression() {
        this.operator = '/';
        this.unit = new NumberExpression(1.0);
        this.applyFunction = (Double a, Double b) -> a / b;
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    @Override
    public Optional<Double> evaluate() {
        if (!this.left.isPresent() || !this.right.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> leftParsed = this.left.get().evaluate();
        Optional<Double> rightParsed = this.right.get().evaluate();

        if (!leftParsed.isPresent() || !rightParsed.isPresent() || rightParsed.get() == 0) {
            return Optional.empty();
        }
        return Optional.of(leftParsed.get() / rightParsed.get());
    }
}
