package jesting.framework.result;

public class TestContextResult {

    private String nameOfTest;
    private TestContextResultType resultType;
    private String checkMessage;
    private Throwable failureCause;
    private double runningTime;

    /**
     * Initializes a new instance of a TestContextResult.
     * 
     * @param testname the name of the test.
     */
    public TestContextResult(String testname) {
        this(testname, TestContextResultType.SUCCESSFULL);
    }

    /**
     * Initializes a new instance of a TestContextResult.
     * 
     * @param testname the name of the test.
     * @param resulttype the type of the result.
     */
    public TestContextResult(String testname, TestContextResultType resulttype) {
        this.nameOfTest = testname;
        this.resultType = resulttype;
    }



    /// getters and setters


    /**
     * Sets the name of the test.
     * 
     * @param testname the name of the test.
     */
    public void setNameOfTest(String testname) {
        this.nameOfTest = testname;
    }

    /**
     * Gets the name of the test.
     */
    public String getNameOfTest() {
        return this.nameOfTest;
    }

    /**
     * Sets the type of the result.
     * 
     * @param resulttype the type of the result.
     */
    public void setResultType(TestContextResultType resulttype) {
        this.resultType = resulttype;
    }

    /**
     * Gets the type of the result.
     */
    public TestContextResultType getResultType() {
        return this.resultType;
    }

    /**
     * Sets the message of the check.
     * 
     * @param message the message of the check.
     */
    public void setCheckMessage(String message) {
        this.checkMessage = message;
    }

    /**
     * Gets the message of the check.
     */
    public String getCheckMessage() {
        return this.checkMessage;
    }

    /**
     * Sets the cause of the failure.
     * 
     * @param ex the exception which causes the failure.
     */
    public void setFailureCause(Throwable ex) {
        this.failureCause = ex;
    }

    /**
     * Gets the cause of the failure.
     */
    public Throwable getFailureCause() {
        return this.failureCause;
    }

    /**
     * Sets the running time of the test.
     * 
     * @param time the time the test took.
     */
    public void setRunningTime(double time) {
        this.runningTime = time;
    }

    /**
     * Gets the running time of the test.
     */
    public double getRunningTime() {
        return this.runningTime;
    }

}