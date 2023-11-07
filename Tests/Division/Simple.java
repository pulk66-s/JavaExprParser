package Tests.Division;

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
        put("Simple Division", Simple.this::test);
        put("Division 0", Simple.this::testZero);
        put("Multiple Division", Simple.this::testMultiple);
    }};

    public TestResult run() {
        Integer passed = 0;
        Integer failed = 0;
        ArrayList<String> failedTests = new ArrayList<String>();

        for (String test : this.tests.keySet()) {
            if (this.tests.get(test).apply(null)) {
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
            String expr = "42 / 3";
            Double expected = 14.0;
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            Optional<Double> resEval = res.get().evaluate();

            return resEval.isPresent() && resEval.get().equals(expected);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean testZero(Void v) {
        try {
            String expr = "42 / 0";
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            Optional<Double> resEval = res.get().evaluate();

            return !resEval.isPresent();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean testMultiple(Void v) {
        try {
            String expr = "42 / 3 / 2";
            Double expected = 7.0;
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            Optional<Double> resEval = res.get().evaluate();

            return resEval.isPresent() && resEval.get().equals(expected);
        } catch (Exception e) {
            return false;
        }
    }
}
