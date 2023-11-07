package Tests.Addition;

import Tests.TestResult;
import Tests.TestSuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;

public class Simple implements TestSuite {
    ArithmeticExpressionFactory factory = new ArithmeticExpressionFactory();
    private HashMap<String, Function<Void, Boolean>> tests = new HashMap<String, Function<Void, Boolean>>() {{
        put("basic", Simple.this::test);
        put("Multiple addition operators", Simple.this::multipleOperators);
        put("With variables", Simple.this::withVariables);
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
            String expr = "42 + 3";
            Double expected = 45.0;
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            
            return res.isPresent() && res.get().evaluate().get().equals(expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean multipleOperators(Void v) {
        try {
            String expr = "42 + 3 + 5";
            Double expected = 50.0;
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            
            return res.isPresent() && res.get().evaluate().get().equals(expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean withVariables(Void v) {
        try {
            String expr = "42 + 3 + x";
            Optional<ArithmeticExpression> res = this.factory.parse(expr);

            if (!res.isPresent()) {
                return false;
            }

            Optional<ArithmeticExpression> simplified = res.get().simplify();
            String expectedString = "45.0 + x";

            if (!simplified.isPresent()) {
                return false;
            }
            return simplified.get().toString().equals(expectedString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
