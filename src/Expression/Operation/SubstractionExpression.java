package Expression.Operation;

import Exception.VariableNotExistError;
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
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() - this.right.evaluate();
    }
}
