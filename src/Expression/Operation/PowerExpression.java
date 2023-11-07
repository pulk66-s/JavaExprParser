package Expression.Operation;

import java.util.HashMap;

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
        this.applyFunction = (Double a, Double b) -> Math.pow(a, b);
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Double> getVariables() {
        return new HashMap<>();
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Double getConstantValue() {
        return 0.0;
    }
}
