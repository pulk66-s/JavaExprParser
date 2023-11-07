package Tests.Substraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import Expression.ArithmeticExpression;
import Expression.ArithmeticExpressionFactory;
import Tests.TestResult;
import Tests.TestSuite;

public class Simplify implements TestSuite {
    ArithmeticExpressionFactory factory = new ArithmeticExpressionFactory();
    private HashMap<String, Function<Void, Boolean>> tests = new HashMap<String, Function<Void, Boolean>>() {{
        put("basic", Simplify.this::test);
        put("multiple variables", Simplify.this::multipleVariables);
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
            String expr = "x + x";
            Optional<ArithmeticExpression> parsed = this.factory.parse(expr);

            if (!parsed.isPresent()) {
                return false;
            }
            
            Optional<ArithmeticExpression> simplified = parsed.get().simplify();

            if (!simplified.isPresent()) {
                return false;
            }

            String stringRepresentation = simplified.get().toString();

            return stringRepresentation.equals("(x * 2.0)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean multipleVariables(Void v) {
        try {
            String expr = "x - x - x - x - x";
            Optional<ArithmeticExpression> parsed = this.factory.parse(expr);

            if (!parsed.isPresent()) {
                return false;
            }
            
            Optional<ArithmeticExpression> simplified = parsed.get().simplify();

            if (!simplified.isPresent()) {
                return false;
            }

            String stringRepresentation = simplified.get().toString();

            return stringRepresentation.equals("(x * 5.0)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }}
