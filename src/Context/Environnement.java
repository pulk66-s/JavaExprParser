package Context;
import java.util.HashMap;

/**
 * @brief   This class contains every informations needed to parse an expression.
 *          It simulates some side effects and environnement that we could have in a real
 *          implementation.
 */
public class Environnement {
    // String of the current expression to parse
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
        this.currExpression = expr.trim();
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
}
