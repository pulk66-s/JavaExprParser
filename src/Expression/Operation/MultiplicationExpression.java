package Expression.Operation;

import java.util.HashMap;
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
        Double multiplicator = 0.0;
        Optional<Double> leftValue = this.left.get().evaluate();
        Optional<Double> rightValue = this.right.get().evaluate();

        if (leftValue.isPresent() && !((Double)Math.abs(leftValue.get())).equals(0.0)) {
            multiplicator = leftValue.get();
        } else if (rightValue.isPresent() && !((Double)Math.abs(rightValue.get())).equals(0.0)) {
            multiplicator = rightValue.get();
        } else {
            multiplicator = 1.0;
        }
        for (String key : rightVariables.keySet()) {
            if (variables.containsKey(key)) {
                variables.put(key, variables.get(key) + rightVariables.get(key));
            } else {
                variables.put(key, rightVariables.get(key));
            }
        }
        for (String key : variables.keySet()) {
            variables.put(key, variables.get(key) * multiplicator);
        }
        return variables;
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Optional<Double> getConstantValue() {
        return this.getConstantValue((Double a, Double b) -> Optional.of(a * b));
    }}
