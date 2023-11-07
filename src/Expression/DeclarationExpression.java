package Expression;

import java.util.HashMap;
import java.util.Optional;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;

/**
 * @brief   This class is used to represent a declaration expression
 * @details This class is used to represent a declaration expression
 *          It extends the ArithmeticExpression class
 */
public class DeclarationExpression extends ArithmeticExpression {
    // The name of the variable
    private String name;

    // The value of the expression
    private ArithmeticExpression value;

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A StatusCode that represent the result of the evaluation
     */
    public StatusCode parse(Environnement env) throws SyntaxError {
        int equalOpIndex = env.findChar('=');

        if (equalOpIndex < 0) {
            return StatusCode.FAILURE;
        }

        String leftExpr = env.getExpression().substring(0, equalOpIndex).trim();
        String rightExpr = env.getExpression().substring(equalOpIndex + 1);

        if (!leftExpr.matches("[a-zA-Z]+")) {
            return StatusCode.FAILURE;
        }
        this.name = leftExpr;
        this.value = new ArithmeticExpressionFactory().parse(rightExpr);
        if (this.value == null) {
            this.value = new MinimalExpressionFactory().parse(rightExpr);
        }
        if (this.value == null) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        Optional<Double> value = this.value.evaluate();

        if (value.isPresent()) {
            Environnement.setVariable(this.name, value.get());
        }
        return Optional.empty();
    }

    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public ArithmeticExpression simplify() throws VariableNotExistError {
        this.value = this.value.simplify();
        return this;
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name);
        sb.append(" = ");
        sb.append(this.value.toStringBuilder());
        return sb;
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    @Override
    public String toString() {
        return this.toStringBuilder().toString();
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Integer> getVariables() {
        return new HashMap<>();
    }
}
