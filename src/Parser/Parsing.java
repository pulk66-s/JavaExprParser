package Parser;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;

/**
 * @brief   This class is used to parse the input file with parsing rules.
 */
public class Parsing {
    // Operation orders
    private static final String[][] orders = {
        {"Declaration"},
        {"Addition", "Substraction"},
        {"Multiplication", "Division", "Power"},
        {"Parantheses"},
        {"Integer", "Variable"}
    };

    /**
     * @brief       This method is used to parse a string and return the result.
     * @param   str The string to be parsed.
     * @return      The expression parsed
     */
    public ArithmeticExpression parse(String str) throws SyntaxError {
        ArithmeticExpressionFactory factory = new ArithmeticExpressionFactory();
        Environnement env = new Environnement(str);

        for (String[] cat : orders) {
            for (String op : cat) {

                ArithmeticExpression expr = factory.create(op);
                StatusCode status = expr.parse(env);

                if (status == StatusCode.SUCCESS) {
                    return expr;
                } else if (status == StatusCode.ERROR) {
                    throw new SyntaxError("Syntax error: " + expr.toString());
                }
            }
        }
        return null;
    }
}
