package Tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import Tests.Addition.Simple;
import Tests.Addition.Simplify;

public class AdditionSuite implements TestSuite {
    private HashMap<String, Function<Void, TestResult>> tests = new HashMap<String, Function<Void, TestResult>>() {{
        put("SimpleAddition", (Void) -> new Simple().run());
        put("Simplification", (Void) -> new Simplify().run());
    }};

    /**
     * @brief   Abstract function to run all the tests
     */
    public TestResult run() {
        Integer passed = 0;
        Integer failed = 0;
        ArrayList<String> failedTests = new ArrayList<String>();

        for (String test : tests.keySet()) {
            TestResult res = tests.get(test).apply(null);
            if (res.failed() == 0) {
                passed++;
            } else {
                failed++;
                failedTests.add(test);
            }
        }
        return new TestResult(passed, failed, failedTests);
    }
}
