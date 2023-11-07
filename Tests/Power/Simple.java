package Tests.Power;

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
        put("variable", Simple.this::testVariable);
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
            String expr = "2 ^ 3";
            Double expected = 8.0;
            java.util.Optional<Expression.ArithmeticExpression> res = this.factory.parse(expr);
            Double resEval = res.get().evaluate().get();
            
            return resEval.equals(expected);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean testVariable(Void v) {
        try {
            String expr = "x ^ 3";
            Optional<ArithmeticExpression> res = this.factory.parse(expr);
            String expected = "(x ^ 3.0)";

            return expected.equals(res.get().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
