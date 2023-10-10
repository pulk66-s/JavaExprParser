package Expression;

import java.util.function.Function;

public interface ExpressionFactory {
    abstract public ArithmeticExpression create(String key);
    abstract public void addKey(String key, Function<Void, ArithmeticExpression> constructor);
}
