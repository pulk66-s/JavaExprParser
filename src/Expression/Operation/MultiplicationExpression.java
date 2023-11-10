package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.ExpressionData;
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
    public MultiplicationExpression(Optional<ArithmeticExpression> left, Optional<ArithmeticExpression> right) {
        this.left = left;
        this.right = right;
        this.applyFunction = (Double a, Double b) -> a * b;
        this.operator = '*';
        this.unit = MinimalExpressionFactory.createConstant(1.0);
        this.nullValue = Optional.of(MinimalExpressionFactory.createConstant(0.0));
    }

    /**
     * Simplify the expression
     * @return The simplified expression
     * @throws VariableNotExistError
     */
    public Optional<ExpressionData> simplify() {
        Optional<ExpressionData> left = this.left.get().simplify();
        Optional<ExpressionData> right = this.right.get().simplify();

        if (!left.isPresent() || !right.isPresent()) {
            return Optional.empty();
        }

        Double constantResult = left.get().constant() * right.get().constant();
        HashMap<String, Double> leftValue = left.get().variables();
        HashMap<String, Double> rightValue = right.get().variables();
        HashMap<String, Double> values = new HashMap<String, Double>();

        for (String lkey : leftValue.keySet()) {
            values.put(lkey, leftValue.get(lkey) * right.get().constant());
        }
        for (String rkey : rightValue.keySet()) {
            values.put(rkey, rightValue.get(rkey) * left.get().constant());
        }
        for (String lkey : leftValue.keySet()) {
            for (String rkey : rightValue.keySet()) {
                values.put(lkey + "*" + rkey, leftValue.get(lkey) * rightValue.get(rkey));
            }
        }
        return Optional.of(new ExpressionData(constantResult, values));
    }
}
