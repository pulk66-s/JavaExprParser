package Expression;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.Operation.Addition;
import Expression.Operation.DivisionExpression;
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
        {"Multiplication", "Division", "Power"},
        {"Parantheses"},
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
    }

    /**
     * @brief       This method is used to parse a string and return the result.
     * @param   str The string to be parsed.
     * @return      The expression parsed
     */
    public ArithmeticExpression parse(String str) throws SyntaxError {
        Environnement env = new Environnement(str);

        for (String[] cat : ArithmeticExpressionFactory.orders) {
            for (String op : cat) {
                ArithmeticExpression expr = this.create(op);
                StatusCode status = expr.parse(env);

                if (status == StatusCode.SUCCESS) {
                    return expr;
                } else if (status == StatusCode.ERROR) {
                    throw new SyntaxError("Syntax error: " + expr.toStringBuilder());
                }
            }
        }
        return null;
    }
}
