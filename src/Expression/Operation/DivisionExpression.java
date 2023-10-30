package Expression.Operation;

import Exception.VariableNotExistError;
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
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Double evaluate() throws VariableNotExistError {
        return this.left.evaluate() / this.right.evaluate();
    }
}
