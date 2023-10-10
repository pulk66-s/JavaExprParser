package Expression;

import Expression.Minimal.FunctionExpression;
import Expression.Minimal.NumberExpression;
import Expression.Minimal.VariableExpression;

public class MinimalExpressionFactory extends AExpressionFactory {
    public MinimalExpressionFactory() {
        constructorMaps.put("Number", (Void) -> new NumberExpression());
        constructorMaps.put("Variable", (Void) -> new VariableExpression());
        constructorMaps.put("Function", (Void) -> new FunctionExpression());
    }
}
