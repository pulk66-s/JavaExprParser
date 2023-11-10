package Expression.Minimal;

import java.util.HashMap;
import java.util.Optional;

import Expression.ArithmeticExpression;

/**
 * @brief   This class is used to represent a minimal expression
 * @details This class is used to represent a minimal expression
 *          It extends the ArithmeticExpression class
 */
public abstract class MinimalExpression extends ArithmeticExpression {
    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Double> getVariables() {
        return new HashMap<>();
    }

    /**
     * @brief   Return the formatted expression to merge values and variables
     * @return  The formatted expression
     */
    public Optional<ArithmeticExpression> mergeVariables() {
        return Optional.of(this);
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Optional<Double> getConstantValue() {
        return Optional.empty();
    }
}
