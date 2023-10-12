package Expression.Minimal;

import java.util.HashMap;
import java.util.function.Function;

import Context.Environnement;
import Context.StatusCode;
import Exception.SyntaxError;
import Exception.VariableNotExistError;
import Expression.ArithmeticExpression;
import Expression.Operation.ParanthesesExpression;
import Parser.Parsing;

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
        this.value = new Parsing().parse(env.getExpression().substring(lparI + 1, rparI));
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

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(this.name);
        sb.append(this.value);
        sb.append(")");
        return sb.toString();
    }
}
