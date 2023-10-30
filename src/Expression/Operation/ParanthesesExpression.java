package Expression.Operation;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Expression.MinimalExpressionFactory;

/**
 * @brief   This class is used to represent a parantheses expression
 * @details This class is used to represent a parantheses expression
 *          It extends the ArithmeticExpression class
 */
public class ParanthesesExpression extends ArithmeticExpression {
    // The value of the expression
    private ArithmeticExpression value;

    /**
     * @brief       This method find the closing parenthesis
     * @param s     The string to search in
     * @return      The index of the closing parenthesis
     */
    static public int findClosingPar(String s) {
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

    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public ArithmeticExpression simplify() throws VariableNotExistError {
        return this.value.simplify();
    }

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A StatusCode that represent the result of the evaluation
     */
    public StatusCode parse(Environnement env) throws SyntaxError {
        int lpar = env.findChar('(');
        int rpar = ParanthesesExpression.findClosingPar(env.getExpression());

        if (lpar == -1 || rpar == -1) {
            if (lpar >= 0 || rpar >= 0) {
                throw new SyntaxError("Missing parenthesis");
            }
            return StatusCode.FAILURE;
        }

        String valueExpr = env.getExpression().substring(lpar + 1, rpar);
        String leftValue = env.getExpression().substring(0, lpar).trim();

        if (leftValue.length() != 0) {
            char lastChar = leftValue.charAt(leftValue.length() - 1);

            if ("+-*/^".indexOf(lastChar) < 0) {
                return StatusCode.FAILURE;
            }
        }
        this.value = new ArithmeticExpressionFactory().parse(valueExpr);
        if (this.value == null) {
            this.value = new MinimalExpressionFactory().parse(valueExpr);
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
    public Double evaluate() throws VariableNotExistError {
        return this.value.evaluate();
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("(");
        sb.append(this.value.toStringBuilder());
        sb.append(")");
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
}
