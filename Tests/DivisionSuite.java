package Tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

import Tests.Division.Simple;

public class DivisionSuite implements TestSuite {
    private HashMap<String, Function<Void, TestResult>> tests = new HashMap<String, Function<Void, TestResult>>() {{
        put("Simple Division", (Void) -> new Simple().run());
    }};

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
