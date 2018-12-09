package jesting.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jesting.framework.result.TestContextResult;
import jesting.framework.result.TestContextResultType;

public class TestContext {

    private Class<?> typeOfTestclass;
    private Method methodToTest;
    private Class<?> expected;
    private int timeout;

    /**
     * Initializes a new testcontext instance.
     */
    public TestContext() {
        this(null);
    }

    /**
     * Initializes a new testcontext instance.
     * 
     * @param type type of the testclass.
     */
    public TestContext(Class<?> type) {
        this(type, null);
    }

    /**
     * Initializes a new testcontext instance.
     * 
     * @param type
     * @param method
     */
    public TestContext(Class<?> type, Method method) {
        this.typeOfTestclass = type;
        this.methodToTest = method;
    }



    /// getters and setters


    /**
     * Sets the type of the testclass.
     * 
     * @param type type of the testclass.
     */
    public void setType(Class<?> type) {
        this.typeOfTestclass = type;
    }

    /**
     * Gets the type of the testclass.
     */
    public Class<?> getType() {
        return this.typeOfTestclass;
    }

    /**
     * Sets the method for the testclass.
     * 
     * @param method the method to test.
     */
    public void setMethod(Method method) {
        this.methodToTest = method;
    }

    /**
     * Gets the method for the testclass.
     */
    public Method getTestMethod() {
        return this.methodToTest;
    }

    /**
     * Sets the expected object for the test.
     * 
     * @param expected the type which is expected by the test.
     */
    public void setExpected(Class<?> expected) {
        this.expected = expected;
    }

    /**
     * Gets the expected object for the test.
     */
    public Class<?> getExpected() {
        return this.expected;
    }

    /**
     * Sets the timeout value in ms.
     * 
     * @param timeout the value in ms.
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Gets the timeout value in ms.
     */
    public int getTimeout() {
        return this.timeout;
    }


    /// public methods 


    /**
     * Gets the name of the test.
     * 
     * @return the name of the testmethod.
     */
    public String getNameOfTest() {
        if (methodToTest == null)
            return null;

        return methodToTest.getName();
    }

    /**
     * Executes the test.
     */
    public TestContextResult run() {
        TestContextResult result = new TestContextResult(typeOfTestclass.getSimpleName(), getNameOfTest());
        double startTime = 0.0;
        double stopTime = 0.0;

        try {
            Constructor<?> ctor = typeOfTestclass.getConstructor();
            Object instanceOfClass = ctor.newInstance();

            startTime = System.nanoTime() / 1E6;
            methodToTest.invoke(instanceOfClass);
            stopTime = System.nanoTime() / 1E6;
           
            result.setRunningTime(stopTime - startTime);
            result.setResultType(TestContextResultType.SUCCESSFULL);
        } catch (InvocationTargetException itex) {
            stopTime = System.nanoTime() / 1E6;
            result.setRunningTime(stopTime - startTime);

            if (itex.getCause() != null && itex.getCause() instanceof AssertionError) {
                result.setResultType(TestContextResultType.ERROR);
                result.setCheckMessage(itex.getCause().getMessage());
            } else if (itex.getCause() != null) {
                if (getExpected() != null && itex.getCause().getClass().equals(expected)) {
                    result.setResultType(TestContextResultType.SUCCESSFULL);
                } else {
                    result.setResultType(TestContextResultType.FAILURE);
                    result.setFailureCause(itex.getCause());
                }
            }
        } catch (Exception ex) {
            result.setResultType(TestContextResultType.FAILURE);
            result.setFailureCause(ex);
        }

        return result;
    }

}