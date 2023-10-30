package Expression.Minimal;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;

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
    public Double evaluate() throws VariableNotExistError {
        return Double.valueOf(this.value);
    }

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A StatusCode that represent the result of the evaluation
     */
    @Override
    public StatusCode parse(Environnement env) throws SyntaxError {
        try {
            this.value = Double.valueOf(env.getExpression());
        } catch (NumberFormatException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    /**
     * @brief   this method return a string representation of the expression
     * @return  a string representation of the expression
     */
    @Override
    public String toString() {
        return this.toStringBuilder().toString();
    }
}
