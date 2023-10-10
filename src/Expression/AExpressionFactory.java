package Expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public abstract class AExpressionFactory implements ExpressionFactory {
    // Hashmap that contains the constructor of the ArithmeticExpression
    protected static HashMap<String, Function<Void, ArithmeticExpression>> constructorMaps = new HashMap<>();    

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
