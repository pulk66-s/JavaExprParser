package Tests;

import java.util.ArrayList;

/**
 * @brief               Class to store the result of a test suite
 * @details             This class is used to store the result of a test suite
 * @param   passed      Number of tests passed
 * @param   failed      Number of tests failed
 * @param   failedTests List of failed tests
 */
public record TestResult(Integer passed, Integer failed, ArrayList<String> failedTests) {
    /**
     * @brief   Function to print the result of a test suite
     */
    public void print() {
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Failed tests:");
        for (String failedTest : failedTests) {
            System.out.println(failedTest);
        }
    }
}
