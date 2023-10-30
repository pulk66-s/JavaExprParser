package Expression.Minimal;

import Context.Environnement;
import Context.StatusCode;
import Exception.VariableNotExistError;

/**
 * @brief   This class is used to represent a variable expression
 * @details This class is used to represent a variable expression
 *          It extends the MinimalExpression class
 */
public class VariableExpression extends MinimalExpression {
    // The name of the variable
    private String name;

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A StatusCode that represent the result of the evaluation
     */
    public StatusCode parse(Environnement env) {
        this.name = env.getExpression().trim();

        if (!this.name.matches("[a-zA-Z]+")) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Double evaluate() throws VariableNotExistError {
        return Environnement.getVariable(name);
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public StringBuilder toStringBuilder() {
        return new StringBuilder(this.name);
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    @Override
    public String toString() {
        return this.name;
    }
}
