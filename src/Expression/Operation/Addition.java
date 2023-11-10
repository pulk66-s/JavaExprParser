package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.ExpressionData;
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
        this.applyFunction = (Double a, Double b) -> a + b;
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
    }

    /**
     * @brief           This method parse an expression and return a StatusCode
     * @param   left    The left expression
     * @param   right   The right expression
     * @return          A boolean that represent the result of the evaluation
     */
    public Addition(Optional<ArithmeticExpression> left, Optional<ArithmeticExpression> right) {
        this.applyFunction = (Double a, Double b) -> a + b;
        this.left = left;
        this.right = right;
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
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

        System.out.println("left: " + left.get());
        System.out.println("right: " + right.get());
        Double constantResult = left.get().constant() + right.get().constant();
        HashMap<String, Double> leftValue = left.get().variables();
        HashMap<String, Double> rightValue = right.get().variables();
        HashMap<String, Double> values = new HashMap<String, Double>();

        values.putAll(leftValue);
        for (String key : rightValue.keySet()) {
            values.merge(key, rightValue.get(key), (a, b) -> a + b);
        }
        System.out.println("values: " + values);
        System.out.println("constantResult: " + constantResult);
        System.out.println("leftValue: " + leftValue);
        System.out.println("rightValue: " + rightValue);
        return Optional.of(new ExpressionData(constantResult, values));
    }
}
