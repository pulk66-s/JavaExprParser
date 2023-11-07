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
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        Optional<Double> leftParsed = this.left.evaluate();
        Optional<Double> rightParsed = this.right.evaluate();

        if (!leftParsed.isPresent() || !rightParsed.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(Math.pow(leftParsed.get(), rightParsed.get()));
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Integer> getVariables() {
        return new HashMap<>();
    }
}
