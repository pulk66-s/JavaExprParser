package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.ExpressionData;
import Expression.OperationExpression;
import Expression.Minimal.NumberExpression;

/**
 * @brief   This class is used to represent a substraction expression
 * @details This class is used to represent a substraction expression
 *          It extends the OperationExpression class
 */
public class SubtractionExpression extends OperationExpression {
    /**
     * @brief   This constructor initialize a Substraction with the left and right expression to 0.0
     */
    public SubtractionExpression() {
        this.operator = '-';
        this.unit = new NumberExpression(0.0);
        this.applyFunction = (Double a, Double b) -> a - b;
    }

    /**
     * @brief       This constructor initialize a Substraction with the left and right expression to the given value
     * @param left  The left expression
     * @param right The right expression
     */
    public SubtractionExpression(Optional<ArithmeticExpression> left, Optional<ArithmeticExpression> right) {
        this.operator = '-';
        this.unit = new NumberExpression(0.0);
        this.left = left;
        this.right = right;
        this.applyFunction = (Double a, Double b) -> a - b;
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
    
        Double constantResult = left.get().constant() - right.get().constant();
        HashMap<String, Double> leftValue = left.get().variables();
        HashMap<String, Double> rightValue = right.get().variables();
        HashMap<String, Double> values = new HashMap<String, Double>();

        values.putAll(leftValue);
        for (String key : rightValue.keySet()) {
            values.merge(key, rightValue.get(key), (a, b) -> a - b);
        }
        return Optional.of(new ExpressionData(constantResult, values));
    }
}
