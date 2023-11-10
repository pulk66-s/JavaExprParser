package Expression;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import Context.Environnement;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.Operation.ParanthesesExpression;

/**
 * @brief   This class is used to represent a function expression
 * @details This class is used to represent a function expression
 *          It extends the MinimalExpression class
 */
public class FunctionExpression extends ArithmeticExpression {
    // The name of the function
    private String name;

    // The value of the function
    private Optional<ArithmeticExpression> value;

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
    public boolean parse(Environnement env) throws SyntaxError {
        int lparI = env.findChar('(');
        int rparI = ParanthesesExpression.findClosingPar(env.getExpression());

        if (lparI < 0 || rparI < 0) {
            return false;
        }
        this.name = env.getExpression().substring(0, lparI).trim();
        if (!this.name.matches("[a-zA-Z]+")) {
            return false;
        }

        String subString = env.getExpression().substring(lparI + 1, rparI);

        this.value = new ArithmeticExpressionFactory().parse(subString);
        if (!this.value.isPresent()) {
            this.value = new MinimalExpressionFactory().parse(subString);
        }
        return this.value.isPresent();
    }

    /**
     * @brief   Return the number of variables of an expression
     * @return  An hashmap containing the variables and the number of occurences
     */
    public HashMap<String, Double> getVariables() {
        return new HashMap<>();
    }

    /**
     * @brief       This method simplify the expression
     * @details     This method simplify the expression by removing all the
     *              useless operations
     * @return      The simplified expression
     * @throws VariableNotExistError
     */
    public Optional<ArithmeticExpression> simplify() {
        return Optional.of(this);
    }

    /**
     * Simplify the expression
     * @return The simplified expression
     */
    public Optional<Double> evaluate() {
        if (!this.value.isPresent()) {
            return Optional.empty();
        }

        Optional<Double> vParsed = this.value.get().evaluate();

        if (!vParsed.isPresent()) {
            return Optional.empty();
        }
        return Optional.of(FunctionExpression.runFunction(this.name, vParsed.get()));
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

        if (!this.value.isPresent()) {
            return sb;
        }
        sb.append("(");
        sb.append(this.name);
        sb.append("(");
        sb.append(this.value.get().toStringBuilder());
        sb.append(")");
        sb.append(")");
        return sb;
    }


    /**
     * @brief   Return the formatted expression to merge values and variables
     * @return  The formatted expression
     */
    public Optional<ArithmeticExpression> mergeVariables() {
        return Optional.of(this);
    }

    /**
     * @brief   Return the constant value of the expression
     * @return  The constant value of the expression
     */
    public Optional<Double> getConstantValue() {
        return Optional.empty();
    }
}
