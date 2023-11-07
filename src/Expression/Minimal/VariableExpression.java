package Expression.Minimal;

import java.util.HashMap;
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
     * @brief           This constructor initialize the name of the variable
     * @param   name    The name of the variable
     */
    public VariableExpression(String name) {
        this.name = name;
    }

    /**
     * @brief   This constructor initialize the name of the variable
     */
    public VariableExpression() {
        this.name = "";
    }

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A boolean that represent the result of the evaluation
     */
    public boolean parse(Environnement env) {
        this.name = env.getExpression().trim();

        return this.name.matches("[a-zA-Z]+");
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        try {
            return Optional.of(Environnement.getVariable(name));
        } catch (VariableNotExistError e) {
            return Optional.empty();
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

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    @Override
     public HashMap<String, Double> getVariables() {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put(this.name, 1.0);
        return variables;
    }
}
