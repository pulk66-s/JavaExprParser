package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Expression.ArithmeticExpression;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

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
        this.unit = new NumberExpression(1.0);
    }

    /**
     * @brief       This method is the constructor of the MultiplicationExpression class
     * @param left  The left expression
     * @param right The right expression
     */
    public MultiplicationExpression(ArithmeticExpression left, ArithmeticExpression right) {
        super(left, right);
        this.operator = '*';
        this.unit = new NumberExpression(1.0);
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        if (!this.left.isPresent() || !this.right.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> leftParsed = this.left.get().evaluate();
        Optional<Double> rightParsed = this.right.get().evaluate();

        if (!leftParsed.isPresent() || !rightParsed.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(leftParsed.get() * rightParsed.get());
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Integer> getVariables() {
        if (!this.left.isPresent() || !this.right.isPresent()) {
            return new HashMap<>();
        }

        HashMap<String, Integer> variables = this.left.get().getVariables();
        HashMap<String, Integer> rightVariables = this.right.get().getVariables();

        for (String key : rightVariables.keySet()) {
            if (variables.containsKey(key)) {
                variables.put(key, variables.get(key) * rightVariables.get(key));
            } else {
                variables.put(key, rightVariables.get(key));
            }
        }
        return variables;
    }
}
