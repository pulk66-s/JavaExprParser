package Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import Expression.Minimal.DivisionExpression;
import Expression.Minimal.IntegerExpression;
import Expression.Minimal.MultiplicationExpression;
import Expression.Minimal.SubstractionExpression;
import Expression.Operation.Addition;

/**
 * @brief   This class is a factory that create an ArithmeticExpression
 */
public class ArithmeticExpressionFactory {
    // Hashmap that contains the constructor of the ArithmeticExpression
    private static HashMap<String, Function<Void, ArithmeticExpression>> constructorMaps = new HashMap<>();

    /**
     * @brief   Factory constructor
     */
    public ArithmeticExpressionFactory() {
        constructorMaps.put("Addition", (Void) -> new Addition());
        constructorMaps.put("Integer", (Void) -> new IntegerExpression());
        constructorMaps.put("Multiplication", (Void) -> new MultiplicationExpression());
        constructorMaps.put("Division", (Void) -> new DivisionExpression());
        constructorMaps.put("Substraction", (Void) -> new SubstractionExpression());
    }

    /**
     * @brief   Get all the existing keys of the factory
     * @return  An array of String that contains all the keys
     */
    public ArrayList<String> getKeys() {
        return new ArrayList<String>(constructorMaps.keySet());
    }

    /**
     * @brief   Add a new key to the factory
     * @param   key The key to add
     * @param   constructor The constructor of the ArithmeticExpression
     */
    public void addKey(String key, Function<Void, ArithmeticExpression> constructor) {
        constructorMaps.put(key, constructor);
    }

    /**
     * @brief       Create a new ArithmeticExpression
     * @param   key The key of the ArithmeticExpression to create
     * @return      The new ArithmeticExpression
     */
    public ArithmeticExpression create(String key) {
        return constructorMaps.get(key).apply(null);
    }
}