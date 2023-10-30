package Expression.Operation;

import Exception.VariableNotExistError;
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
        this.operator = '+';
        this.unit = new NumberExpression(0.0);
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() + this.right.evaluate();
    }
}
