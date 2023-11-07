package Tests.Substraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Tests.TestResult;
import Tests.TestSuite;

public class Simple implements TestSuite {
    ArithmeticExpressionFactory factory = new ArithmeticExpressionFactory();
    private HashMap<String, Function<Void, Boolean>> tests = new HashMap<String, Function<Void, Boolean>>() {{
        put("basic", Simple.this::test);
        put("Multiple substraction operators", Simple.this::multipleOperators);
        put("With variables", Simple.this::withVariables);
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
            String expr = "42 - 3";
            Double expected = 39.0;
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            Double resEval = res.get().evaluate().get();

            return resEval.equals(expected);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean multipleOperators(Void v) {
        try {
            String expr = "42 - 3 - 5";
            Double expected = 34.0;
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            Double resEval = res.get().evaluate().get();
            
            return resEval.equals(expected);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean withVariables(Void v) {
        try {
            String expr = "x - 3 - 42";
            String expected = "(-45.0 + x)";
            Optional<ArithmeticExpression> res = this.factory.parse(expr).get().simplify();

            if (!res.isPresent()) {
                return false;
            }

            String stringRepresentation = res.get().toString();

            return stringRepresentation.equals(expected);
        } catch (Exception e) {
            return false;
        }
    }
}
