package main.jesting.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class TestRunner {

    private ArrayList<TestContext> tests;

    /**
     * Constructs an instance of the TestRunner
     * 
     * @see main.jesting.framework.TestRunner
     */
    public TestRunner() {
        this.tests = new ArrayList<TestContext>();
    }



    /// public methods


    /**
     * Adds a testclass to the test queue. 
     * 
     * @param type the class which holds the tests
     */
    public void addTest(Class<?> type) {
        if (type == null)
            return;

        List<Method> methods = getMethodsAnnotatedWith(type, Test.class);

        if (methods == null || methods.size() == 0)
            return;

        for (Method method : methods) {
            TestContext testContext = new TestContext(type, method);
            this.tests.add(testContext);
        }
    }

    /**
     * Runs all the tests.
     */
    public void run() {
        if (tests.isEmpty())
            return;

        for (TestContext test : tests) {
            test.run();
        }
    }


    /// private methods

    
    /**
     * Returns the methods with the annotation.
     * 
     * @param type the type of the testclass.
     * @param annotation the annotation above the method.
     */
    private List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
        if (type == null || annotation == null)
        return null;

        final List<Method> methods = new ArrayList<Method>();
        final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(type.getDeclaredMethods()));

        for (final Method method : allMethods) {
            if (method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }

        return methods;
    }
}