package Expression;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Expression.Minimal.FunctionExpression;
import Expression.Minimal.NumberExpression;
import Expression.Minimal.VariableExpression;

public class MinimalExpressionFactory extends AExpressionFactory {
    private static final String[] minimalValues = {
        "Function", "Number", "Variable"
    };

    public MinimalExpressionFactory() {
        constructorMaps.put("Number", (Void) -> new NumberExpression());
        constructorMaps.put("Variable", (Void) -> new VariableExpression());
        constructorMaps.put("Function", (Void) -> new FunctionExpression());
    }

    /**
     * @brief       This method is used to parse a string and return the result.
     * @param   str The string to be parsed.
     * @return      The expression parsed
     */
    public ArithmeticExpression parse(String str) throws SyntaxError {
        MinimalExpressionFactory minimalFactory = new MinimalExpressionFactory();
        Environnement env = new Environnement(str);


        for (String op : MinimalExpressionFactory.minimalValues) {
            ArithmeticExpression expr = minimalFactory.create(op);
            StatusCode status = expr.parse(env);

            if (status == StatusCode.SUCCESS) {
                return expr;
            } else if (status == StatusCode.ERROR) {
                throw new SyntaxError("Syntax error: " + expr.toStringBuilder());
            }
        }
        return null;
    }}
