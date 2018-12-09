package jesting.framework.result;

public class TestContextResult {

    private String nameOfTestClass;
    private String nameOfTest;
    private TestContextResultType resultType;
    private String checkMessage;
    private Throwable failureCause;
    private double runningTime;
    private int timeout;
    private boolean istimedout;

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
     * @param nameOfTestClass the name of the testclass.
     * @param testname the name of the test.
     */
    public TestContextResult(String nameOfTestClass, String testname) {
        this(nameOfTestClass, testname, TestContextResultType.SUCCESSFULL);
    }

    /**
     * Initializes a new instance of a TestContextResult.
     * 
     * @param testname the name of the test.
     * @param resulttype the type of the result.
     */
    public TestContextResult(String testname, TestContextResultType resulttype) {
        this("", testname, resulttype);
    }


    /**
     * Initializes a new instance of a TestContextResult.
     * 
     * @param nameOfTestClass the name of the testclass.
     * @param testname the name of the test.
     * @param resulttype the type of the result.
     */
    public TestContextResult(String nameOfTestClass, String testname, TestContextResultType resulttype) {
        this.nameOfTestClass = nameOfTestClass;
        this.nameOfTest = testname;
        this.resultType = resulttype;
    }



    /// getters and setters


    /**
     * Sets the name of the testclass.
     * 
     * @param testclassname the name of the testclass.
     */
    public void setNameOfTestClass(String testclassname) {
        this.nameOfTestClass = testclassname;
    }

    /**
     * Gets the name of the testclass.
     */
    public String getNameOfTestClass() {
        return this.nameOfTestClass;
    }

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

    /**
     * Sets the timeout value in ms.
     * @param timeout the timout in ms.
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Gets the timeout value.
     */
    public int getTimeout() {
        return this.timeout;
    }

    /**
     * Sets the flag, which signals that the test is timed out.
     * 
     * @param timedout the flag, if the test is timed out.
     */
    public void setIsTimedOut(boolean timedout) {
        this.istimedout = timedout;
    }

    /**
     * Signals, if the test is timed out.
     */
    public boolean isTimedOut() {
        return this.istimedout;
    }

    ///

}