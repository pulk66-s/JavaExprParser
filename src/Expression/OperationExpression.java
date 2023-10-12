package Expression;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Parser.Parsing;

public abstract class OperationExpression extends ArithmeticExpression {
    protected ArithmeticExpression left, right;
    protected char operator;
    protected ArithmeticExpression unit;

    public OperationExpression() {
    }

    public int findHighestOperator(String expr) {
        int nbParenthesis = 0;

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == '(') {
                nbParenthesis++;
            } else if (c == ')') {
                nbParenthesis--;
            } else if (c == this.operator) {
                if (nbParenthesis == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    public StatusCode parse(Environnement env) throws SyntaxError {
        int opIndex = this.findHighestOperator(env.getExpression());

        if (opIndex < 0) {
            return StatusCode.FAILURE;
        }

        String left_expr = env.getExpression().substring(0, opIndex);
        String right_expr = env.getExpression().substring(opIndex + 1);

        if (left_expr.length() == 0 || right_expr.length() == 0) {
            return StatusCode.FAILURE;
        }
        this.left = new Parsing().parse(left_expr);
        this.right = new Parsing().parse(right_expr);
        return StatusCode.SUCCESS;
    }

    public void simplify() throws VariableNotExistError {
        if (Math.abs(this.left.evaluate() - this.unit.evaluate()) < 0.00000001) {
            this.left = this.unit;
        }
        if (Math.abs(this.right.evaluate() - this.unit.evaluate()) < 0.00000001) {
            this.right = this.unit;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(this.left.toString());
        sb.append(" " + this.operator + " ");
        sb.append(this.right.toString());
        sb.append(")");
        return sb.toString();
    }
}
