package Tests;

import java.util.ArrayList;

public class Main {
    static public void main(String[] args) {
        ArrayList<TestSuite> testSuites = new ArrayList<TestSuite>();

        testSuites.add(new AdditionSuite());
        for (TestSuite suite : testSuites) {
            TestResult res = suite.run();

            if (res.failed() > 0) {
                System.out.println("Failed tests:");
                for (String test : res.failedTests()) {
                    System.out.println(test);
                }
            } else {
                System.out.println("All tests passed");
            }
        }
    }
}
