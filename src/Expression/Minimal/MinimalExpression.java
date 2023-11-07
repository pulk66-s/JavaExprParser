package Expression.Minimal;

import java.util.HashMap;

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

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Integer> getVariables() {
        return new HashMap<>();
    }
}
