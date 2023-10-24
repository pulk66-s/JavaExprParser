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

public class FunctionExpression extends MinimalExpression {
    private String name;
    private ArithmeticExpression value;
    public static HashMap<String, Function<Double, Double>> functions = new HashMap<String, Function<Double, Double>>() {{
        put("sin", (Double x) -> Math.sin(x));
        put("cos", (Double x) -> Math.cos(x));
        put("tan", (Double x) -> Math.tan(x));
        put("sqrt", (Double x) -> Math.sqrt(x));
        put("abs", (Double x) -> Math.abs(x));
        put("log", (Double x) -> Math.log(x));
    }};

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

    public void simplify() throws VariableNotExistError {
        this.value.simplify();
    }

    public Double evaluate() throws VariableNotExistError {
        return FunctionExpression.runFunction(this.name, this.value.evaluate());
    }

    public static void addFunction(String name, Function<Double, Double> function) {
        FunctionExpression.functions.put(name, function);
    }

    public static Double runFunction(String name, Double value) {
        return FunctionExpression.functions.get(name).apply(value);
    }

    public StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(this.name);
        sb.append(this.value.toStringBuilder());
        sb.append(")");
        return sb;
    }
}
