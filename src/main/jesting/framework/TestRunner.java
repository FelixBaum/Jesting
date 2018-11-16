package main.jesting.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.jesting.framework.result.TestContextResult;
import main.jesting.framework.result.TestContextResultType;

/**
 * 
 */
public class TestRunner {

    private ArrayList<TestContext> tests;
    private ArrayList<TestContextResult> successfullTests;
    private ArrayList<TestContextResult> failureTests;
    private ArrayList<TestContextResult> errorTests;

    /**
     * Constructs an instance of the TestRunner
     * 
     * @see main.jesting.framework.TestRunner
     */
    public TestRunner() {
        this.tests = new ArrayList<TestContext>();
        resetResults();
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

            Test annotation = method.getAnnotation(Test.class);
            if (annotation.expected() != null) {
                testContext.setExpected(annotation.expected());
            }

            this.tests.add(testContext);
        }
    }

    /**
     * Runs all the tests.
     */
    public void run() {
        resetResults();

        if (tests.isEmpty())
            return;

        List<TestContextResult> results = new ArrayList<TestContextResult>();    

        /// Parallel
        tests.parallelStream().forEach((test) -> {
            TestContextResult result = test.run();
            results.add(result);
        });
        ///

        for (TestContextResult result : results) {
            switch(result.getResultType()) {
                case SUCCESSFULL:
                    successfullTests.add(result);
                    break;
                case FAILURE:
                    failureTests.add(result);
                    break;
                case ERROR:
                    errorTests.add(result);
                    break;
            }

            System.out.println(result.getNameOfTest() + ": " + result.getResultType() + " took: " + result.getRunningTime() + " ms");
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

    /**
     * Resets the results of the previous tests.
     */
    private void resetResults() {
        this.successfullTests = new ArrayList<TestContextResult>();
        this.failureTests = new ArrayList<TestContextResult>();
        this.errorTests = new ArrayList<TestContextResult>();
    }
}