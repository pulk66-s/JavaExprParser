package Expression.Minimal;

import java.util.Optional;

import Context.Environnement;
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
     * @return      A boolean that represent the result of the evaluation
     */
    public boolean parse(Environnement env) {
        this.name = env.getExpression().trim();

        if (!this.name.matches("[a-zA-Z]+")) {
            return false;
        }
        return true;
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        try {
            return Optional.of(Environnement.getVariable(name));
        } catch (VariableNotExistError e) {
            return Optional.of(0.0);
        }
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
