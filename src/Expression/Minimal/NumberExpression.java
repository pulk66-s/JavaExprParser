package Expression.Minimal;

import java.util.HashMap;
import java.util.Optional;

import Context.Environnement;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.ExpressionData;

/**
 * @brief   This class is used to represent a number expression
 */
public class NumberExpression extends MinimalExpression {
    // The value of the number
    Double value;

    /**
     * @brief   This constructor initialize a Number with the value to 0.0
     */
    public NumberExpression() {
        this.value = 0.0;
    }

    /**
     * @brief       This constructor initialize a Number with the value to the given value
     * @param value The value of the number
     */
    public NumberExpression(Double value) {
        this.value = value;
    }

    /**
     * @brief   This method return a string builder representation of the expression
     * @return  a string builder representation of the expression
     */
    public StringBuilder toStringBuilder() {
        return new StringBuilder(this.value.toString());
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    @Override
    public Optional<Double> evaluate() {
        return Optional.of(Double.valueOf(this.value));
    }

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A boolean that represent the result of the evaluation
     */
    @Override
    public boolean parse(Environnement env) throws SyntaxError {
        try {
            this.value = Double.valueOf(env.getExpression());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    @Override
    public Optional<Double> getConstantValue() {
        return Optional.of(this.value);
    }

    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public Optional<ExpressionData> simplify() {
        return Optional.of(new ExpressionData(this.value, new HashMap<>()));
    }
}
