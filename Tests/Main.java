package Tests;

import java.util.ArrayList;

public class Main {
    static public void main(String[] args) {
        ArrayList<TestSuite> testSuites = new ArrayList<TestSuite>();

        testSuites.add(new AdditionSuite());
        testSuites.add(new MultiplicationSuite());
        testSuites.add(new SubstractionSuite());
        testSuites.add(new DivisionSuite());
        for (TestSuite suite : testSuites) {
            System.out.println("Starting suite");
            TestResult res = suite.run();

            if (res.failed() > 0) {
                System.out.println("Failed tests:");
                System.out.println(res.failedTests());
            } else {
                System.out.println("All tests passed");
            }
        }
    }
}
