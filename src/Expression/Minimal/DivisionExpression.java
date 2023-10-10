package Expression.Minimal;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.OperationExpression;

public class DivisionExpression extends OperationExpression {
    public DivisionExpression() {
        this.operator = '/';
    }

    public StatusCode parse(Environnement env) throws SyntaxError {
        return super.parse(env);
    }

    @Override
    public Double evaluate() {
        return this.left.evaluate() / this.right.evaluate();
    }
}
