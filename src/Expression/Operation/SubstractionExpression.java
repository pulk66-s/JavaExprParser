package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Expression.ArithmeticExpression;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

/**
 * @brief   This class is used to represent a substraction expression
 * @details This class is used to represent a substraction expression
 *          It extends the OperationExpression class
 */
public class SubstractionExpression extends OperationExpression {
    /**
     * @brief   This constructor initialize a Substraction with the left and right expression to 0.0
     */
    public SubstractionExpression() {
        this.operator = '-';
        this.unit = new NumberExpression(0.0);
    }

    /**
     * @brief       This constructor initialize a Substraction with the left and right expression to the given value
     * @param left  The left expression
     * @param right The right expression
     */
    public SubstractionExpression(ArithmeticExpression left, ArithmeticExpression right) {
        this.operator = '-';
        this.unit = new NumberExpression(0.0);
        this.left = Optional.of(left);
        this.right = Optional.of(right);
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
        return Optional.of(leftParsed.get() - rightParsed.get());
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Double> getVariables() {
        if (!this.left.isPresent() || !this.right.isPresent()) {
            return new HashMap<>();
        }

        HashMap<String, Double> variables = this.left.get().getVariables();
        HashMap<String, Double> rightVariables = this.right.get().getVariables();

        for (String key : rightVariables.keySet()) {
            if (variables.containsKey(key)) {
                variables.put(key, variables.get(key) - rightVariables.get(key));
            } else {
                variables.put(key, -rightVariables.get(key));
            }
        }
        return variables;
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Double getConstantValue() {
        return this.getConstantValue((Double a, Double b) -> a - b);
    }
}
