package Expression;

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
}
