package jesting.framework;

import jesting.framework.result.*;

/**
 * An interface which will be notified, if a test has finished or a test-run is done.
 */
public interface TestProgressListener {

    /**
     * Is called, when a testmethod has finished.
     */
    void notifyProgress(TestResult result);

    /**
     * Is called, when a test-run has finished.
     */
    void notifyResult();

}