package jesting.framework.result;

import java.io.*;

public class TestResult {

    private String nameOfTestClass;
    private String nameOfTest;
    private TestContextResultType resultType;
    private String checkMessage;
    private Throwable failureCause;
    private double duration;
    private int timeout;
    private boolean istimedout;

    /**
     * Initializes a new instance of a TestResult.
     * 
     * @param contextresult the result of the testcontext.
     */
    public TestResult(TestContextResult contextresult) {
        if (contextresult == null)
            return;

        this.nameOfTestClass = contextresult.getNameOfTestClass();
        this.nameOfTest = contextresult.getNameOfTest();
        this.resultType = contextresult.getResultType();
        this.checkMessage = contextresult.getCheckMessage();
        this.failureCause = contextresult.getFailureCause();
        this.duration = contextresult.getRunningTime();
        this.timeout = contextresult.getTimeout();
        this.istimedout = contextresult.isTimedOut();
    }

    /// getters

    /**
     * Gets the name of the testclass.
     */
    public String getNameOfTestClass() {
        return this.nameOfTestClass;
    }

    /**
     * Gets the name of the test.
     */
    public String getNameOfTest() {
        return this.nameOfTest;
    }

    /**
     * Gets the type of the result.
     */
    public TestContextResultType getResultType() {
        return this.resultType;
    }

    /**
     * Gets the message of the check.
     */
    public String getCheckMessage() {
        return this.checkMessage;
    }

    /**
     * Gets the cause of the failure.
     */
    public Throwable getFailureCause() {
        return this.failureCause;
    }

    /**
     * Gets the running time of the test.
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Gets the timeout value in ms.
     */
    public int getTimeout() {
        return this.timeout;
    }

    /**
     * Gets the signal, if the test is timed out.
     */
    public boolean isTimedOut() {
        return this.istimedout;
    }

    ///

    /// public methods

    /**
     * Gets the result message as a String.
     * 
     * @return the result message.
     */
    public String getResultMessage() {
        String description = "";

        switch (getResultType()) {
            case SUCCESSFULL:
                description = getNameOfTestClass() + "." + getNameOfTest() + ": " + getResultType() + " - duration: " + getDuration() + " ms";
                break;
            case FAILURE:
                description = getNameOfTestClass() + "." + getNameOfTest() + ": " + getResultType() + " - " + getFailureCause().getMessage() + " - duration: " + getDuration() + " ms";
                break;
            case ERROR:
                if (isTimedOut()) {
                    description = getNameOfTestClass() + "." + getNameOfTest() + ": " + getResultType() + " - Test exceeded the duration of " + getTimeout() + " ms timeout";
                } else {
                    if (getCheckMessage() != null && !getCheckMessage().equals(""))
                        description = getNameOfTestClass() + "." + getNameOfTest() + ": " + getResultType() + " - Message: \"" + getCheckMessage() + "\" - duration: " + getDuration() + " ms";
                    else 
                        if (getFailureCause() != null)
                            description = getNameOfTestClass() + "." + getNameOfTest() + ": " + getResultType() + " - Message: \"" + getFailureCause().getMessage() + "\" - duration: " + getDuration() + " ms";
                        else
                            description = getNameOfTestClass() + "." + getNameOfTest() + ": " + getResultType() + " - duration: " + getDuration() + " ms";
                }
                break;
        }

        return description;
    }

    /**
     * Prints the result message to the PrintStream.
     * 
     * @param stream the PrintStream onto which the message will be printed.
     */
    public void printResultMessage(PrintStream stream) {
        if (stream == null)
            return;

        String description = getResultMessage();
        stream.println(description);
    }

    ///
}