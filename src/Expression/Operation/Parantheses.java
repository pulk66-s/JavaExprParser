package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.ArithmeticExpression;
import Parser.Parsing;

public class Parantheses extends ArithmeticExpression {
    private ArithmeticExpression value;

    @Override
    public StatusCode parse(Environnement env) throws SyntaxError {
        int lpar = env.findChar('(');
        int rpar = env.findLastChar(')');

        if (lpar == -1 || rpar == -1) {
            if (lpar >= 0 || rpar >= 0) {
                throw new SyntaxError("Missing parenthesis");
            }
            return StatusCode.FAILURE;
        }

        String valueExpr = env.getExpression().substring(lpar + 1, rpar);

        this.value = new Parsing().parse(valueExpr);
        return StatusCode.SUCCESS;
    }

    @Override
    public Double evaluate() {
        return this.value.evaluate();
    }

    public String toString() {
        return "(" + this.value.toString() + ")";
    }
}
