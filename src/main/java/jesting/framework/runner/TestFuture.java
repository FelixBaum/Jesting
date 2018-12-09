package jesting.framework.runner;

import java.util.concurrent.Future;

import jesting.framework.TestContext;
import jesting.framework.result.TestContextResult;

public class TestFuture {

    private TestContext test;
    private Future<TestContextResult> future;

    /**
     * Initializes a new instance of the TestFuture class.
     * 
     * @param test the test to run.
     * @param future the Future for the ExecutorService.
     */
    public TestFuture(TestContext test, Future<TestContextResult> future) {
        this.test = test;
        this.future = future;
    }

    /**
     * Gets the test.
     */
    public TestContext getTest() {
        return this.test;
    }

    /**
     * Gets the future.
     */
    public Future<TestContextResult> getFuture() {
        return this.future;
    }

}