package Expression;

import java.util.Optional;
import java.util.function.BiFunction;

import Context.Environnement;
import Exception.SyntaxError;

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
    protected Optional<ArithmeticExpression> left = Optional.empty();
    protected Optional<ArithmeticExpression> right = Optional.empty();
    protected char operator;
    protected ArithmeticExpression unit;
    protected Optional<ArithmeticExpression> nullValue = Optional.empty();
    protected BiFunction<Double, Double, Double> applyFunction = (a, b) -> a;

    /**
     * @brief   Constructor of the OperationExpression class
     * @details It overrides the ArithmeticExpression constructor
     */
    public OperationExpression() {
    }

    /**
     * @brief       Parse the expression
     * @param env   The current environnement
     * @return      The status code of the parsing
     * @throws SyntaxError
     */
    public boolean parse(Environnement env) throws SyntaxError {
        int opIndex = env.findLowestOperator(this.operator, env.getExpression());

        if (opIndex < 0) {
            return false;
        }

        String left_expr = env.getExpression().substring(0, opIndex);
        String right_expr = env.getExpression().substring(opIndex + 1);

        if (left_expr.length() == 0 || right_expr.length() == 0) {
            return false;
        }
        this.left = new ArithmeticExpressionFactory().parse(left_expr);
        if (!this.left.isPresent()) {
            this.left = new MinimalExpressionFactory().parse(left_expr);
        }
        this.right = new ArithmeticExpressionFactory().parse(right_expr);
        if (!this.right.isPresent()) {
            this.right = new MinimalExpressionFactory().parse(right_expr);
        }
        return this.right.isPresent() && this.left.isPresent();
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        if (!this.left.isPresent() || !this.right.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> leftParsed = this.left.get().evaluate();
        Optional<Double> rightParsed = this.right.get().evaluate();

        if (!leftParsed.isPresent() || !rightParsed.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(this.applyFunction.apply(leftParsed.get(), rightParsed.get()));
    }

    /**
     * @brief   Convert the expression to a string builder
     * @return  The string builder of the expression
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();

        if (!this.left.isPresent() || !this.right.isPresent()) {
            return sb;
        }
        sb.append("(");
        sb.append(this.left.get().toStringBuilder());
        sb.append(" " + this.operator + " ");
        sb.append(this.right.get().toStringBuilder());
        sb.append(")");
        return sb;
    }

    /**
     * @brief           Set the left arithmetic expression
     * @param   left    The left arithmetic expression
     */
    public void setLeft(ArithmeticExpression left) {
        this.left = Optional.of(left);
    }

    /**
     * @brief   Return the left expression
     * @return  The left expression
     */
    public Optional<ArithmeticExpression> getLeft() {
        return this.left;
    }

    /**
     * @brief   Return the right expression
     * @return  The right expression
     */
    public Optional<ArithmeticExpression> getRight() {
        return this.right;
    }

    /**
     * @brief           Set the right arithmetic expression
     * @param   right    The right arithmetic expression
     */
    public void setRight(ArithmeticExpression right) {
        this.right = Optional.of(right);
    }
}
