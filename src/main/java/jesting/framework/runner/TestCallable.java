package jesting.framework.runner;

import java.util.concurrent.Callable;

import jesting.framework.context.TestContext;
import jesting.framework.result.TestContextResult;

/**
 * A class for the ExecutorService.
 */
public class TestCallable implements Callable<TestContextResult> {

    private TestContext test;

    /**
     * Initializes a new instance of the TestCallable.
     * 
     * @param test the test to run.
     */
    public TestCallable(TestContext test) {
        this.test = test;
    }

    /**
     * Is called, when the callable is used.
     */
    @Override
    public TestContextResult call() throws Exception {
        return test.run();
    }

}