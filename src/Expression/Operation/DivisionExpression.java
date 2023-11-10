package Expression.Operation;

import java.util.HashMap;
import java.util.Optional;

import Exception.VariableNotExistError;
import Expression.ExpressionData;
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

        Double constantResult = left.get().constant() / right.get().constant();
        HashMap<String, Double> leftValue = left.get().variables();
        HashMap<String, Double> rightValue = right.get().variables();
        HashMap<String, Double> values = new HashMap<String, Double>();

        for (String lkey : leftValue.keySet()) {
            values.put(lkey, leftValue.get(lkey) / right.get().constant());
        }
        for (String rkey : rightValue.keySet()) {
            values.put(rkey, rightValue.get(rkey) / left.get().constant());
        }
        for (String lkey : leftValue.keySet()) {
            for (String rkey : rightValue.keySet()) {
                values.put(lkey + "*" + rkey, leftValue.get(lkey) / rightValue.get(rkey));
            }
        }
        return Optional.of(new ExpressionData(constantResult, values));
    }
}
