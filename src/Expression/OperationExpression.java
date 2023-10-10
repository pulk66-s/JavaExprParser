package Expression;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Parser.Parsing;

public abstract class OperationExpression extends ArithmeticExpression {
    protected ArithmeticExpression left, right;
    protected char operator;

    public OperationExpression() {
    }

    @Override
    public StatusCode parse(Environnement env) throws SyntaxError {
        int opIndex = env.findChar(this.operator);
        int pIndex = env.findChar('(');

        if (opIndex < 0 || (pIndex >= 0 && pIndex < opIndex)) {
            return StatusCode.FAILURE;
        }

        String left_expr = env.getExpression().substring(0, opIndex);
        String right_expr = env.getExpression().substring(opIndex + 1);

        this.left = new Parsing().parse(left_expr);
        this.right = new Parsing().parse(right_expr);
        return StatusCode.SUCCESS;
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
