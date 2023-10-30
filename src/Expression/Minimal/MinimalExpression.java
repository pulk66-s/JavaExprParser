package Expression.Minimal;

import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;

/**
 * @brief   This class is used to represent a minimal expression
 * @details This class is used to represent a minimal expression
 *          It extends the ArithmeticExpression class
 */
public abstract class MinimalExpression extends ArithmeticExpression {
    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public ArithmeticExpression simplify() {
        return this;
    }
}
