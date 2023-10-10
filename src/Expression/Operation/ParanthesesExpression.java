package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.ArithmeticExpression;
import Parser.Parsing;

public class ParanthesesExpression extends ArithmeticExpression {
    private ArithmeticExpression value;

    private int findClosingPar(String s) {
        int parnb = 0;
        boolean firstParFound = false;
        int foundIndex = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (firstParFound == true && (c == '(' || c == ')')) {
                parnb += c == '(' ? 1 : -1;
                if (parnb == 0) {
                    foundIndex = i;
                    break;
                }
            } else if (c == '(') {
                firstParFound = true;
                parnb++;
            }
        }
        return foundIndex;
    }

    @Override
    public StatusCode parse(Environnement env) throws SyntaxError {
        int lpar = env.findChar('(');
        int rpar = this.findClosingPar(env.getExpression());

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
