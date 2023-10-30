package Expression;

import java.util.function.Function;

/**
 * @brief   This interface is used to represent an expression factory
 * @details This interface is used to represent an expression factory
 */
public interface ExpressionFactory {
    /**
     * @brief       Create a new ArithmeticExpression with the given key
     * @param key   The key of the ArithmeticExpression to create
     * @return      The new ArithmeticExpression
     */
    abstract public ArithmeticExpression create(String key);

    /**
     * @brief               Add a new key to the factory
     * @param key           The key to add
     * @param constructor   The constructor of the ArithmeticExpression
     */
    abstract public void addKey(String key, Function<Void, ArithmeticExpression> constructor);
}
