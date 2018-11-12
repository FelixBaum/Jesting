package main.jesting.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestContext {

    private Class<?> typeOfTestclass;
    private Method methodToTest;

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
    public void run() {
        try {
            Constructor<?> ctor = typeOfTestclass.getConstructor();
            Object instanceOfClass = ctor.newInstance();

            methodToTest.invoke(instanceOfClass);
        } catch (InvocationTargetException itex) {
            if (itex.getCause() != null && itex.getCause() instanceof AssertionError) {
                System.err.println(itex.getCause().getMessage());
            }
        } catch (Exception ex) {
        }
    }

}