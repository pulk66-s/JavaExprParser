package Expression.Minimal;

import java.util.HashMap;
import java.util.function.Function;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Expression.MinimalExpressionFactory;
import Expression.Operation.ParanthesesExpression;

/**
 * @brief   This class is used to represent a function expression
 * @details This class is used to represent a function expression
 *          It extends the MinimalExpression class
 */
public class FunctionExpression extends MinimalExpression {
    // The name of the function
    private String name;

    // The value of the function
    private ArithmeticExpression value;

    // The list of functions
    public static HashMap<String, Function<Double, Double>> functions = new HashMap<String, Function<Double, Double>>() {{
        put("sin", (Double x) -> Math.sin(x));
        put("cos", (Double x) -> Math.cos(x));
        put("tan", (Double x) -> Math.tan(x));
        put("sqrt", (Double x) -> Math.sqrt(x));
        put("abs", (Double x) -> Math.abs(x));
        put("log", (Double x) -> Math.log(x));
    }};

    /**
     * @brief       Parse the expression
     * @param env   The current environnement
     * @return      The status code of the parsing
     * @throws SyntaxError
     */
    public StatusCode parse(Environnement env) throws SyntaxError {
        int lparI = env.findChar('(');
        int rparI = ParanthesesExpression.findClosingPar(env.getExpression());

        if (lparI < 0 || rparI < 0) {
            return StatusCode.FAILURE;
        }
        this.name = env.getExpression().substring(0, lparI).trim();
        if (!this.name.matches("[a-zA-Z]+")) {
            return StatusCode.FAILURE;
        }

        String subString = env.getExpression().substring(lparI + 1, rparI);

        this.value = new ArithmeticExpressionFactory().parse(subString);
        if (this.value == null) {
            this.value = new MinimalExpressionFactory().parse(subString);
        }
        if (this.value == null) {
            return StatusCode.FAILURE;
        }
        return StatusCode.SUCCESS;
    }

    /**
     * Simplify the expression
     * @return The simplified expression
     * @throws VariableNotExistError
     */
    public Double evaluate() throws VariableNotExistError {
        return FunctionExpression.runFunction(this.name, this.value.evaluate());
    }

    /**
     * @brief           Add a function to the list of functions
     * @param name      The name of the function
     * @param function  The function
     */
    public static void addFunction(String name, Function<Double, Double> function) {
        FunctionExpression.functions.put(name, function);
    }

    /**
     * @brief       This method run a function from the list of functions
     * @param name  The name of the function
     * @param value The value of the function
     * @return      The result of the function
     */
    public static Double runFunction(String name, Double value) {
        return FunctionExpression.functions.get(name).apply(value);
    }

    /**
     * @brief       This method return the actual expression into a string builder
     * @return      The actual expression into a string builder
     */
    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(this.name);
        sb.append(this.value.toStringBuilder());
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
