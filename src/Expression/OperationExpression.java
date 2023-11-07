package Expression;

import java.util.HashMap;
import java.util.Optional;

import Context.Environnement;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.Operation.Addition;

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

    /**
     * @brief   Constructor of the OperationExpression class
     * @details It overrides the ArithmeticExpression constructor
     */
    public OperationExpression() {
    }

    /**
     * @brief       Constructor of the OperationExpression class with two expressions
     * @param left  The left expression
     * @param right The right expression
     * @return      The OperationExpression created
     */
    public OperationExpression(ArithmeticExpression left, ArithmeticExpression right) {
        this.left = Optional.of(left);
        this.right = Optional.of(right);
    }

    /**
     * @brief       Constructor of the OperationExpression class
     * @param expr  The expression to parse
     * @return      The index of the highest operator in the expression
     */
    public int findLowestOperator(String expr) {
        int nbParenthesis = 0;

        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);

            if (c == ')') {
                nbParenthesis++;
            } else if (c == '(') {
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
    public boolean parse(Environnement env) throws SyntaxError {
        int opIndex = this.findLowestOperator(env.getExpression());

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
     * Simplify the expression with unit value
     * @param   left    The left expression
     * @param   right   The right expression
     * @return          The simplified expression
     */
    private Optional<ArithmeticExpression> unitSimplify(
        Optional<ArithmeticExpression> left, Optional<ArithmeticExpression> right
    ) {
        Optional<Double> unitRes = this.unit.evaluate();

        if (!left.isPresent() || !right.isPresent() || !unitRes.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> leftRes = left.get().evaluate();
        Optional<Double> rightRes = right.get().evaluate();

        if (leftRes.isPresent() && leftRes.get().equals(unitRes.get())) {
            return this.right;
        }
        if (rightRes.isPresent() && rightRes.get().equals(unitRes.get())) {
            return this.left;
        }
        return Optional.empty();
    }

    /**
     * Simplify the expression with null value
     * @param   left    The left expression
     * @param   right   The right expression
     * @return          The simplified expression
     */
    private Optional<ArithmeticExpression> nullSimplified(
        Optional<ArithmeticExpression> left, Optional<ArithmeticExpression> right
    ) {
        if (!this.nullValue.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> nullRes = this.nullValue.get().evaluate();

        if (!left.isPresent() || !right.isPresent() || !nullRes.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> leftRes = left.get().evaluate();
        Optional<Double> rightRes = right.get().evaluate();

        if ((leftRes.isPresent() && leftRes.get().equals(nullRes.get()))
        || (rightRes.isPresent() && rightRes.get().equals(nullRes.get()))) {
            return this.nullValue;
        }
        return Optional.empty();
    }

    /**
     * Simplify the expression
     * @return The simplified expression
     * @throws VariableNotExistError
     */
    public Optional<ArithmeticExpression> simplify() throws VariableNotExistError {
        if (!this.left.isPresent() || !this.right.isPresent()) {
            return Optional.empty();
        }

        Optional<ArithmeticExpression> unitSimplified;
        Optional<ArithmeticExpression> nullSimplified = Optional.empty();

        unitSimplified = this.unitSimplify(this.left, this.right);
        if (unitSimplified.isPresent()) {
            return unitSimplified;
        }
        if (this.nullValue.isPresent()) {
            Optional<Double> nValue = this.nullValue.get().evaluate();

            if (nValue.isPresent()) {
                nullSimplified = this.nullSimplified(this.left, this.right);
            }
            if (nullSimplified.isPresent()) {
                return nullSimplified;
            }
        }
        this.left = this.left.get().simplify();
        this.right = this.right.get().simplify();
        Optional<ArithmeticExpression> res = this.mergeVariables();
        return res;
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
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    @Override
    public String toString() {
        return this.toStringBuilder().toString();
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

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public abstract HashMap<String, Double> getVariables();

    /**
     * @brief   Return the formatted expression to merge values and variables
     * @return  The formatted expression
     */
    public Optional<ArithmeticExpression> mergeVariables() {
        HashMap<String, Double> variables = this.getVariables();
        Double constantValue = this.getConstantValue();
        Addition exprRes = new Addition();

        variables = variables.entrySet().stream()
            .filter(entry -> !entry.getValue().equals(0.0))
            .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), HashMap::putAll);
        if (variables.keySet().isEmpty()) {
            return Optional.of(MinimalExpressionFactory.createConstant(constantValue));
        }
        for (String key : variables.keySet()) {
            ArithmeticExpression mult;
            
            if (variables.get(key).equals(0.0)) {
                continue;
            } else if (variables.get(key).equals(1.0)) {
                mult = MinimalExpressionFactory.createVariable(key);
            } else {
                mult = ArithmeticExpressionFactory.createMultiplication(
                    MinimalExpressionFactory.createVariable(key),
                    MinimalExpressionFactory.createConstant(variables.get(key))
                );
            }

            if (!exprRes.getRight().isPresent()) {
                exprRes.setRight(mult);
            } else {
                Addition nAdd = new Addition();

                exprRes.setLeft(mult);
                nAdd.setRight(exprRes);
                exprRes = nAdd;
            }
        }
        if (constantValue.equals(0.0)) {
            return exprRes.getRight();
        }
        exprRes.setLeft(MinimalExpressionFactory.createConstant(constantValue));
        return Optional.of(exprRes);
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public abstract Double getConstantValue();
}
