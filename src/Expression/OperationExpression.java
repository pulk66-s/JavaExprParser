package Expression;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;

/**
 * @brief   This class is used to represent an operation expression
 * @details This class is used to represent an operation expression, it contains
 *          the left and right expression and the operator.
 *          It also contains the unit of the expression. Used to simplify the
 *          expression.
 *          It is an abstract class, it must be extended to be used.
 *          It implements the ArithmeticExpression interface.
 */
public abstract class OperationExpression extends ArithmeticExpression {
    protected ArithmeticExpression left, right;
    protected char operator;
    protected ArithmeticExpression unit;

    /**
     * @brief   Constructor of the OperationExpression class
     * @details It overrides the ArithmeticExpression constructor
     */
    public OperationExpression() {
    }

    /**
     * @brief       Constructor of the OperationExpression class
     * @param expr  The expression to parse
     * @return      The index of the highest operator in the expression
     */
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

    /**
     * @brief       Parse the expression
     * @param env   The current environnement
     * @return      The status code of the parsing
     * @throws SyntaxError
     */
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
        this.left = new ArithmeticExpressionFactory().parse(left_expr);
        if (this.left == null) {
            this.left = new MinimalExpressionFactory().parse(left_expr);
        }
        this.right = new ArithmeticExpressionFactory().parse(right_expr);
        if (this.right == null) {
            this.right = new MinimalExpressionFactory().parse(right_expr);
        }
        if (this.right == null || this.left == null) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    /**
     * Simplify the expression
     * @return The simplified expression
     * @throws VariableNotExistError
     */
    public ArithmeticExpression simplify() throws VariableNotExistError {
        if (Math.abs(this.left.evaluate() - this.unit.evaluate()) < 0.00000001) {
            return this.right;
        }
        if (Math.abs(this.right.evaluate() - this.unit.evaluate()) < 0.00000001) {
            return this.left;
        }
        return this;
    }

    /**
     * @brief   Convert the expression to a string builder
     * @return  The string builder of the expression
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(this.left.toStringBuilder());
        sb.append(" " + this.operator + " ");
        sb.append(this.right.toStringBuilder());
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
