package Expression;

import java.util.HashMap;
import java.util.Optional;

import Context.Environnement;
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
    private Optional<ArithmeticExpression> value;

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A boolean that represent the result of the evaluation
     */
    public boolean parse(Environnement env) throws SyntaxError {
        int equalOpIndex = env.findChar('=');

        if (equalOpIndex < 0) {
            return false;
        }

        String leftExpr = env.getExpression().substring(0, equalOpIndex).trim();
        String rightExpr = env.getExpression().substring(equalOpIndex + 1);

        if (!leftExpr.matches("[a-zA-Z]+")) {
            return false;
        }
        this.name = leftExpr;
        this.value = new ArithmeticExpressionFactory().parse(rightExpr);
        if (!this.value.isPresent()) {
            this.value = new MinimalExpressionFactory().parse(rightExpr);
        }
        if (!this.value.isPresent()) {
            this.value = Optional.empty();
            return false;
        }
        return true;
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        if (!this.value.isPresent()) {
            return Optional.empty();
        }
        Optional<Double> value = this.value.get().evaluate();

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
    public Optional<ArithmeticExpression> simplify() throws VariableNotExistError {
        if (!this.value.isPresent()) {
            return Optional.empty();
        }
        this.value = this.value.get().simplify();
        return Optional.of(this);
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        
        if (!this.value.isPresent()) {
            return sb;
        }
        sb.append(this.name);
        sb.append(" = ");
        sb.append(this.value.get().toStringBuilder());
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
    public HashMap<String, Double> getVariables() {
        return new HashMap<>();
    }

    /**
     * @brief   Return the formatted expression to merge values and variables
     * @return  The formatted expression
     */
    public Optional<ArithmeticExpression> mergeVariables() {
        return Optional.of(this);
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Double getConstantValue() {
        return 0.0;
    }
}
