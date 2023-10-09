package Context;
import java.util.HashMap;

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
}
