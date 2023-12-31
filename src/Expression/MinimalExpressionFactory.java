package Expression;

import java.util.Optional;

import Context.Environnement;
import Exception.SyntaxError;
import Expression.Minimal.NumberExpression;
import Expression.Minimal.VariableExpression;

/**
 * @brief   This class is used to represent a minimal expression factory
 * @details This class is used to represent a minimal expression factory
 *          It extends the AExpressionFactory class
 */
public class MinimalExpressionFactory extends AExpressionFactory {
    /**
     * @brief   This array contains all the minimal values
     */
    private static final String[] minimalValues = {
        "Number", "Variable"
    };

    /**
     * @brief   This constructor initialize the constructorMaps
     */
    public MinimalExpressionFactory() {
        constructorMaps.put("Number", (Void) -> new NumberExpression());
        constructorMaps.put("Variable", (Void) -> new VariableExpression());
    }

    /**
     * @brief       This method is used to parse a string and return the result.
     * @param   str The string to be parsed.
     * @return      The expression parsed
     */
    public Optional<ArithmeticExpression> parse(String str) throws SyntaxError {
        MinimalExpressionFactory minimalFactory = new MinimalExpressionFactory();
        Environnement env = new Environnement(str);


        for (String op : MinimalExpressionFactory.minimalValues) {
            ArithmeticExpression expr = minimalFactory.create(op);
            boolean status = expr.parse(env);

            if (status == true) {
                return Optional.of(expr);
            }
        }
        return Optional.empty();
    }

    /**
     * @brief           This method create a new constant from a double
     * @param   value   The value of the constant
     * @return          The constant created
     */
    static public NumberExpression createConstant(Double value) {
        return new NumberExpression(value);
    }

    /**
     * @brief           This method create a new variable from a string
     * @param   name    The name of the variable
     * @return          The variable created
     */
    static public VariableExpression createVariable(String name) {
        return new VariableExpression(name);
    }
}
