package Expression;

import java.util.Optional;

import Context.Environnement;
import Exception.SyntaxError;
import Expression.Operation.Addition;
import Expression.Operation.DivisionExpression;
import Expression.Operation.FactorialExpression;
import Expression.Operation.MultiplicationExpression;
import Expression.Operation.ParanthesesExpression;
import Expression.Operation.PowerExpression;
import Expression.Operation.SubstractionExpression;

/**
 * @brief   This class is a factory that create an ArithmeticExpression
 */
public class ArithmeticExpressionFactory extends AExpressionFactory {

    // Operation orders
    private static final String[][] orders = {
        {"Declaration"},
        {"Addition", "Substraction"},
        {"Factorial", "Multiplication", "Division", "Power"},
        {"Function", "Parantheses"},
    };

    /**
     * @brief   Factory constructor
     */
    public ArithmeticExpressionFactory() {
        constructorMaps.put("Addition", (Void) -> new Addition());
        constructorMaps.put("Multiplication", (Void) -> new MultiplicationExpression());
        constructorMaps.put("Division", (Void) -> new DivisionExpression());
        constructorMaps.put("Substraction", (Void) -> new SubstractionExpression());
        constructorMaps.put("Parantheses", (Void) -> new ParanthesesExpression());
        constructorMaps.put("Power", (Void) -> new PowerExpression());
        constructorMaps.put("Declaration", (Void) -> new DeclarationExpression());
        constructorMaps.put("Function", (Void) -> new FunctionExpression());
        constructorMaps.put("Factorial", (Void) -> new FactorialExpression());
    }

    /**
     * @brief       This method is used to parse a string and return the result.
     * @param   str The string to be parsed.
     * @return      The expression parsed
     */
    public Optional<ArithmeticExpression> parse(String str) throws SyntaxError {
        Environnement env = new Environnement(str);

        for (String[] cat : ArithmeticExpressionFactory.orders) {
            for (String op : cat) {
                ArithmeticExpression expr = this.create(op);
                boolean status = expr.parse(env);

                if (status == true) {
                    return Optional.of(expr);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * @brief       This method create a new multiplication from a two expressions
     * @param left  The left expression
     * @param right The right expression
     * @return      The multiplication created
     */
    static public MultiplicationExpression createMultiplication(ArithmeticExpression left, ArithmeticExpression right) {
        return new MultiplicationExpression(left, right);
    }

    /**
     * @brief       This method create a new division from a two expressions
     * @param left  The left expression
     * @param right The right expression
     * @return      The division created
     */
    static public SubstractionExpression createSubstraction(ArithmeticExpression left, ArithmeticExpression right) {
        return new SubstractionExpression(left, right);
    }

    /**
     * @brief           This method create an addition from two expressions
     * @param   left    The left expression
     * @param   right   The right expression
     * @return          The addition created
     */
    static public Addition createAddition(ArithmeticExpression left, ArithmeticExpression right) {
        return new Addition(left, right);
    }
}
