package Tests.Multiplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import Expression.ArithmeticExpressionFactory;
import Tests.TestResult;
import Tests.TestSuite;

public class Simplify implements TestSuite {
    private ArithmeticExpressionFactory factory = new ArithmeticExpressionFactory();
    private HashMap<String, Function<Void, Boolean>> tests = new HashMap<String, Function<Void, Boolean>>() {{
        put("basic", Simplify.this::test);
    }};

    public TestResult run() {
        Integer passed = 0;
        Integer failed = 0;
        ArrayList<String> failedTests = new ArrayList<String>();

        for (String test : tests.keySet()) {
            if (tests.get(test).apply(null)) {
                passed++;
            } else {
                failed++;
                failedTests.add(test);
            }
        }
        return new TestResult(passed, failed, failedTests);
    }

    private boolean test(Void v) {
        try {
            String expr = "2 * x + 3 * x";
            java.util.Optional<Expression.ArithmeticExpression> parsed = this.factory.parse(expr);

            if (!parsed.isPresent()) {
                return false;
            }
            
            java.util.Optional<Expression.ArithmeticExpression> simplified = parsed.get().simplify();

            if (!simplified.isPresent()) {
                return false;
            }

            String stringRepresentation = simplified.get().toString();

            return stringRepresentation.equals("(x * 5.0)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
