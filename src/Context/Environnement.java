package Context;
import java.util.HashMap;

import Exception.VariableNotExistError;

/**
 * @brief   This class contains every informations needed to parse an expression.
 *          It simulates some side effects and environnement that we could have in a real
 *          implementation.
 */
public class Environnement {
    // String of the current expression to parse
    private String currExpression;

    // Hashmap that represent a variable table
    static private HashMap<String, Double> variables = new HashMap<String, Double>();

    /**
     * @brief   Constructor of the Environment class
     */
    public Environnement() {
        this.currExpression = "";
    }

    /**
     * @brief       Constructor
     * @param expr  The expression to parse
     */
    public Environnement(String expr) {
        this.currExpression = expr.trim();
    }

    /**
     * @brief       Setter of the evironnement expression
     * @param expr  The expression to set
     */
    public void setExpression(String expr) {
        this.currExpression = expr;
    }

    /**
     * @brief   This method is used to find a character in the current expression
     * @param c The character to find
     * @return  The index of the character in the expression or -1 if not found
     */
    public int findChar(char c) {
        return this.currExpression.indexOf(c);
    }

    /**
     * @brief   This method is used to find the last character in the current expression
     * @param c The character to find
     * @return  The index of the character in the expression or -1 if not found
     */
    public int findLastChar(char c) {
        return this.currExpression.lastIndexOf(c);
    }

    /**
     * @brief   This method is used to get the current expression
     * @return  The current expression
     */
    public String getExpression() {
        return this.currExpression;
    }

    /**
     * @brief           Set a variable in the environnement
     * @param   name    The name of the var
     * @param   value   The value of the expr
     */
    static public void setVariable(String name, Double value) {
        variables.put(name, value);
    }

    /**
     * @brief           Get a variable value
     * @param   name    The name of the variable
     * @return          The value of the variable
     */
    static public Double getVariable(String name) throws VariableNotExistError {
        if (variables.containsKey(name)) {
            return variables.get(name);
        } else {
            throw new VariableNotExistError(name);
        }
    }

    /**
     * @brief   Get the variable table
     * @return  The variable table
     */
    public HashMap<String, Double> getVariables() {
        return variables;
    }

    /**
     * @brief       Constructor of the OperationExpression class
     * @param expr  The expression to parse
     * @return      The index of the highest operator in the expression
     */
    public int findLowestOperator(char operator, String expr) {
        int nbParenthesis = 0;

        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);

            if (c == ')') {
                nbParenthesis++;
            } else if (c == '(') {
                nbParenthesis--;
            } else if (c == operator) {
                if (nbParenthesis == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
}
