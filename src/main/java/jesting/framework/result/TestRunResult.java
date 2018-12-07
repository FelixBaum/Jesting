package jesting.framework.result;

import java.util.List;

public class TestRunResult {

    private List<TestResult> successfullTests;
    private List<TestResult> failedTests;
    private List<TestResult> errorTests;

    /**
     * Initializes a new instance of the TestRunResult.
     * 
     * @param successfullTests the tests, which are successfull.
     * @param failedTests the tests, which are failed.
     * @param errorTests the tests, which has errors.
     */
    public TestRunResult(List<TestResult> successfullTests, List<TestResult> failedTests, List<TestResult> errorTests) {
        this.successfullTests = successfullTests;
        this.failedTests = failedTests;
        this.errorTests = errorTests;
    }


    /// public methods 

    /**
     * Returns the count of all tests.
     */
    public int getCountOfAllTests() {
        int count = 0;
        if (successfullTests != null) count += successfullTests.size();
        if (failedTests != null) count+= failedTests.size();
        if (errorTests != null) count+= errorTests.size();
        return count;
    }

    /**
     * Returns the count of successfull tests.
     */
    public int getCountOfSuccessfullTests() {
        if (successfullTests != null) return successfullTests.size();
        return 0;
    }

    /**
     * Returns the count of failed tests.
     */
    public int getCountOfFailedTests() {
        if (failedTests != null) return failedTests.size();
        return 0;
    }

    /**
     * Returns the count of error tests.
     */
    public int getCountOfErrorTests() {
        if (errorTests != null) return errorTests.size();
        return 0;
    }

    /**
     * Returns the successfull tests.
     */
    public List<TestResult> getSuccessfullTests() {
        return this.successfullTests;
    }

    /**
     * Returns the failed tests.
     */
    public List<TestResult> getFailedTests() {
        return this.failedTests;
    }

    /**
     * Returns the error tests.
     */
    public List<TestResult> getErrorTests() {
        return this.errorTests;
    }

    
    ///

}