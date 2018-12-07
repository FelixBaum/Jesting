package jesting.framework.result;

import java.io.*;

public class TestResult {

    private String nameOfTest;
    private TestContextResultType resultType;
    private String checkMessage;
    private Throwable failureCause;
    private double duration;

    /**
     * Initializes a new instance of a TestResult.
     * 
     * @param contextresult the result of the testcontext.
     */
    public TestResult(TestContextResult contextresult) {
        if (contextresult == null)
            return;

        this.nameOfTest = contextresult.getNameOfTest();
        this.resultType = contextresult.getResultType();
        this.checkMessage = contextresult.getCheckMessage();
        this.failureCause = contextresult.getFailureCause();
        this.duration = contextresult.getRunningTime();
    }

    /// getters

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
                description = getNameOfTest() + ": " + getResultType() + " - duration: " + getDuration() + " ms";
                break;
            case FAILURE:
                description = getNameOfTest() + ": " + getResultType() + " - " + getFailureCause().getMessage() + " - duration: " + getDuration() + " ms";
                break;
            case ERROR:
                if (getCheckMessage() != null && !getCheckMessage().equals(""))
                    description = getNameOfTest() + ": " + getResultType() + " - Message: \"" + getCheckMessage() + "\" - duration: " + getDuration() + " ms";
                else 
                    if (getFailureCause() != null)
                        description = getNameOfTest() + ": " + getResultType() + " - Message: \"" + getFailureCause().getMessage() + "\" - duration: " + getDuration() + " ms";
                    else
                        description = getNameOfTest() + ": " + getResultType() + " - duration: " + getDuration() + " ms";
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