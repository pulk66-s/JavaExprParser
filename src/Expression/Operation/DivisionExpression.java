package Expression.Operation;

import java.util.HashMap;

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
                variables.put(key, variables.get(key) / rightVariables.get(key));
            } else {
                variables.put(key, rightVariables.get(key));
            }
        }
        return variables;
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Double getConstantValue() {
        return this.getConstantValue((Double a, Double b) -> a / b);
    }}
