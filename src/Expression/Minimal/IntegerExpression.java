package Expression.Minimal;

public class IntegerExpression extends ArithmeticException {
    Integer value;

    public IntegerExpression(Integer value) {
        this.value = value;
    }

    public String toString() {
        return value.toString();
    }
}
