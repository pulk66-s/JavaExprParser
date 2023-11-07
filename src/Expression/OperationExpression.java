package Expression;

import java.util.HashMap;
import java.util.Optional;

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
    protected Optional<ArithmeticExpression> nullValue = Optional.empty();

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
     * Simplify the expression with unit value
     * @param   left    The left value
     * @param   right   The right value
     * @param   unit    The unit value
     * @return          The simplified expression
     */
    private Optional<ArithmeticExpression> unitSimplify(
        Double left, Double right, Double unit
    ) {
        if (Math.abs(left - unit) < 0.00000001) {
            return Optional.of(this.right);
        }
        if (Math.abs(right - unit) < 0.00000001) {
            return Optional.of(this.left);
        }
        return Optional.empty();
    }

    /**
     * Simplify the expression with null value
     * @param   left    The left value
     * @param   right   The right value
     * @param   nValue  The null value
     * @return          The simplified expression
     */
    private Optional<ArithmeticExpression> nullSimplified(
        Double left, Double right, Double unit
    ) {
        if (Math.abs(left - unit) < 0.00000001 || Math.abs(right - unit) < 0.00000001) {
            return Optional.of(MinimalExpressionFactory.createConstant(0.0));
        }
        return Optional.empty();
    }

    /**
     * Simplify the expression
     * @return The simplified expression
     * @throws VariableNotExistError
     */
    public ArithmeticExpression simplify() throws VariableNotExistError {
        Optional<Double> left = this.left.evaluate();
        Optional<Double> right = this.right.evaluate();
        Optional<Double> unit = this.unit.evaluate();
        Optional<ArithmeticExpression> unitSimplified;
        Optional<ArithmeticExpression> nullSimplified = Optional.empty();

        if (!left.isPresent() || !right.isPresent()) {
            return this;
        }
        unitSimplified = this.unitSimplify(
            left.get(), right.get(), unit.get()
        );
        if (unitSimplified.isPresent()) {
            return unitSimplified.get();
        }
        if (this.nullValue.isPresent()) {
            Optional<Double> nValue = this.nullValue.get().evaluate();

            if (nValue.isPresent()) {
                nullSimplified = this.nullSimplified(
                    left.get(), right.get(), nValue.get()
                );
            }
            if (nullSimplified.isPresent()) {
                return nullSimplified.get();
            }
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

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public abstract HashMap<String, Integer> getVariables();
}
