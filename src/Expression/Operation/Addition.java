package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

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
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
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
        return Optional.of(leftParsed.get() + rightParsed.get());
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Integer> getVariables() {
        HashMap<String, Integer> variables = this.left.getVariables();
        HashMap<String, Integer> rightVariables = this.right.getVariables();

        for (String key : rightVariables.keySet()) {
            if (variables.containsKey(key)) {
                variables.put(key, variables.get(key) + rightVariables.get(key));
            } else {
                variables.put(key, rightVariables.get(key));
            }
        }
        return variables;
    }
}
