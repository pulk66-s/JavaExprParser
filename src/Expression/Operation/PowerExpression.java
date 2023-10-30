package Expression.Operation;

import Exception.VariableNotExistError;
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
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Double evaluate() throws VariableNotExistError {
        return Math.pow(this.left.evaluate(), this.right.evaluate());
    }
}
