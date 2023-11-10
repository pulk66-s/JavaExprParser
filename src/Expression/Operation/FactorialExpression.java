package Expression.Operation;

import java.util.Optional;

import Context.Environnement;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Expression.ExpressionData;
import Expression.MinimalExpressionFactory;

public class FactorialExpression extends ArithmeticExpression {
    private Optional<ArithmeticExpression> value;

    /**
     * @brief   This constructor initialize an Addition with the left and right expression to 0.0
     */
    public FactorialExpression() {
        this.value = Optional.empty();
    }

    /**
     * @brief           This method parse an expression and return a StatusCode
     * @param value     The value to apply the factorial
     */
    public FactorialExpression(ArithmeticExpression value) {
        this.value = Optional.of(value);
    }

    /**
     * @brief       This method parse an expression and return a StatusCode
     * @param   env The environnement that contains the expression to parse
     * @return      A boolean that represent the result of the evaluation
     */
    public boolean parse(Environnement env) throws SyntaxError {
        int opIndex = env.findLowestOperator('!', env.getExpression());

        if (opIndex < 0) {
            return false;
        }

        String leftExpr = env.getExpression().substring(0, opIndex);
        String leftValue = "";
        int i = leftExpr.length() - 1;

        for (; leftExpr.charAt(i) == ' '; i--);

        Character c = leftExpr.charAt(i);

        if (c == ')') {
            while (c != '(') {
                leftValue = c + leftValue;
                i--;
                c = leftExpr.charAt(i);
            }
        } else {
            do {
                c = leftExpr.charAt(i);
                leftValue = c + leftValue;
                i--;
            } while ((Character.isDigit(c) || Character.isAlphabetic(c)) && i > 0);
        }
        if (leftValue.length() == 0) {
            return false;
        }
        this.value = new ArithmeticExpressionFactory().parse(leftExpr);
        if (!this.value.isPresent()) {
            this.value = new MinimalExpressionFactory().parse(leftExpr);
        }
        return this.value.isPresent();
    }

    /**
     * @brief       Apply the factorial to the value
     * @param value The value to apply the factorial
     * @return      The result of the factorial
     */
    private Double applyFactorial(Double value) {
        Double result = 1.0;

        for (int i = 1; i <= value; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * @brief       This method evaluate the value stored after parsing
     * @return      The result of the expression
     */
    public Optional<Double> evaluate() {
        if (!this.value.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> value = this.value.get().evaluate();

        if (!value.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(this.applyFactorial(value.get()));
    }

    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public Optional<ExpressionData> simplify() {
        if (!this.value.isPresent()) {
            return Optional.empty();
        }

        return this.value.get().simplify();
    }

    /**
     * @brief   This method return a string representation of the expression
     * @return  A string representation of the expression
     */
    public StringBuilder toStringBuilder() {
        StringBuilder str = new StringBuilder();

        if (!this.value.isPresent()) {
            return str;
        }
        str.append(this.value.get().toStringBuilder());
        str.append('!');
        return str;
    }
}
