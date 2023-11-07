package Expression;

import java.util.HashMap;
import java.util.Optional;

import Context.Environnement;
import Exception.SyntaxError;
import Exception.VariableNotExistError;

public abstract class ArithmeticExpression {
    protected ArithmeticExpression() {
    }

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A boolean that represent the result of the evaluation
     */
    public abstract boolean parse(Environnement env) throws SyntaxError;

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public abstract Optional<Double> evaluate();

    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public abstract Optional<ArithmeticExpression> simplify() throws VariableNotExistError;

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public abstract StringBuilder toStringBuilder();

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public abstract String toString();

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public abstract HashMap<String, Double> getVariables();

    /**
     * @brief   Return the formatted expression to merge values and variables
     * @return  The formatted expression
     */
    public abstract Optional<ArithmeticExpression> mergeVariables();

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public abstract Double getConstantValue();
}
