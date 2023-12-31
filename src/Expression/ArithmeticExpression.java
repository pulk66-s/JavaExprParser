package Expression;

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
    public abstract Optional<ExpressionData> simplify();

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public abstract StringBuilder toStringBuilder();

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public String toString() {
        return toStringBuilder().toString();
    }
}
