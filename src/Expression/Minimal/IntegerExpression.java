package Expression.Minimal;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.ArithmeticExpression;

public class IntegerExpression extends ArithmeticExpression {
    Integer value;

    public IntegerExpression() {
        this.value = 0;
    }

    public IntegerExpression(Integer value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }

    @Override
    public Double evaluate() {
        return Double.valueOf(this.value);
    }

    @Override
    public StatusCode parse(Environnement env) throws SyntaxError {
        try {
            this.value = Integer.parseInt(env.getExpression());
        } catch (NumberFormatException e) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }
}
