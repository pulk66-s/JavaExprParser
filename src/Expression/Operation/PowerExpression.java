package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

/**
 * @brief   This class is used to represent a power expression
 * @details This class is used to represent a power expression
 *          It extends the OperationExpression class
 */
public class PowerExpression extends OperationExpression {
    /**
     * @brief   This constructor initialize a Power with the left and right expression to 1.0
     */
    public PowerExpression() {
        this.operator = '^';
        this.unit = new NumberExpression(1.0);
        this.applyFunction = (Double a, Double b) -> Math.pow(a, b);
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

        if (!leftParsed.isPresent() || !rightParsed.isPresent() || (leftParsed.get() == 0 && rightParsed.get() == 0)) {
            return Optional.empty();
        }
        return Optional.of(this.applyFunction.apply(leftParsed.get(), rightParsed.get()));
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Double> getVariables() {
        return new HashMap<>();
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Optional<Double> getConstantValue() {
        return Optional.empty();
    }
}
