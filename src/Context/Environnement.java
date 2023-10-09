package Context;
import java.util.HashMap;

import Expression.ArithmeticExpression;

/**
 * @brief   This class contains every informations needed to evaluate an expression.
 *          It simulates some side effects and environnement that we could have in a real
 *          implementation.
 */
public class Environnement {
    // String of the current expression to evaluate
    private String currExpression;

    // Hashmap that represent a variable table
    private HashMap<String, Variable> variables;

    /**
     * @brief   Constructor of the Environment class
     */
    public Environnement() {
        this.variables = new HashMap<String, Variable>();
        this.currExpression = "";
    }

    /**
     * @brief       Constructor
     * @param expr  The expression to parse
     */
    public Environnement(String expr) {
        this.currExpression = expr;
        this.variables = new HashMap<String, Variable>();
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
     * @brief           This method is used to find a number in the current expression
     * @param opIndex   The index of the operator
     * @param isRight   True if the number to find is on the right of the operator
     * @return          The number found or null if not found
     */
    public int findNumber(int opIndex, boolean isRight) {
        int i = opIndex + (isRight ? 1 : -1);
        int j = i;

        while (i >= 0 && i < this.currExpression.length() && Character.isDigit(this.currExpression.charAt(i))) {
            i += isRight ? 1 : -1;
        }

        if (i == j) {
            return -1;
        }

        return Integer.parseInt(this.currExpression.substring(i + (isRight ? -1 : 1), j + (isRight ? 1 : -1)));
    }
}
