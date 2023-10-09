package Expression;

import Context.Environnement;
import Context.StatusCode;

public abstract class ArithmeticExpression {
    protected ArithmeticExpression() {
    }

    /**
     * @brief       This method evaluate an expression and return a StatusCode
     * @param   env The environnement that contains the expression to evaluate
     * @return      A StatusCode that represent the result of the evaluation
     */
    public abstract StatusCode evaluate(Environnement env);
}
