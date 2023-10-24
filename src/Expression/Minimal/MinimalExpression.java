package Expression.Minimal;

import Expression.ArithmeticExpression;

public abstract class MinimalExpression extends ArithmeticExpression {
    public ArithmeticExpression simplify() {
        return this;
    }
}
