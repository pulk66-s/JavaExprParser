package Tests.Factorial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Tests.TestResult;
import Tests.TestSuite;

public class Simple implements TestSuite {
    private ArithmeticExpressionFactory factory = new ArithmeticExpressionFactory();
    private HashMap<String, Function<Void, Boolean>> tests = new HashMap<String, Function<Void, Boolean>>() {{
        put("basic", Simple.this::test);
        put("nested", Simple.this::nest);
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
            String expr = "5!";
            Optional<ArithmeticExpression> parsed = this.factory.parse(expr);
            Double expected = 120.0;

            if (!parsed.isPresent()) {
                return false;
            }
            
            Optional<Double> value = parsed.get().evaluate();

            if (!value.isPresent()) {
                return false;
            }
            return value.get().equals(expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean nest(Void v) {
        try {
            String expr = "1 + 5! + 1";
            Optional<ArithmeticExpression> parsed = this.factory.parse(expr);
            Double expected = 122.0;

            if (!parsed.isPresent()) {
                return false;
            }
            
            Optional<Double> value = parsed.get().evaluate();

            if (!value.isPresent()) {
                return false;
            }
            return value.get().equals(expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
