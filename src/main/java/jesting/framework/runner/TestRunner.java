package jesting.framework.runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jesting.framework.Test;
import jesting.framework.TestContext;
import jesting.framework.result.TestContextResult;
import jesting.framework.result.TestResult;
import jesting.framework.result.TestRunResult;
import jesting.framework.listener.TestProgressListener;

/**
 * 
 */
public class TestRunner {

    private ArrayList<TestProgressListener> listeners;
    private ArrayList<TestContext> tests;

    /**
     * Constructs an instance of the TestRunner
     * 
     * @see main.jesting.framework.TestRunner
     */
    public TestRunner() {
        this.listeners = new ArrayList<TestProgressListener>();
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

            Test annotation = method.getAnnotation(Test.class);
            if (annotation.expected() != null) {
                testContext.setExpected(annotation.expected());
            }

            this.tests.add(testContext);
        }
    }

    /**
     * Adds a listener to the runner, which will be notified on result changes.
     * 
     * @param listener the listener to add
     */
    public void addProgressListener(TestProgressListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Runs all the tests.
     */
    public void run() {
        if (tests.isEmpty())
            return;

        List<TestContextResult> results = new ArrayList<TestContextResult>();    
        List<TestResult> successfullTests = new ArrayList<TestResult>();
        List<TestResult> failedTests = new ArrayList<TestResult>();
        List<TestResult> errorTests = new ArrayList<TestResult>();


        /// Parallel
        tests.parallelStream().forEach((test) -> {
            TestContextResult result = test.run();

            notifyListenersProgress(result);

            results.add(result);
        });
        ///


        for (TestContextResult result : results) {
            switch(result.getResultType()) {
                case SUCCESSFULL:
                    successfullTests.add(new TestResult(result));
                    break;
                case FAILURE:
                    failedTests.add(new TestResult(result));
                    break;
                case ERROR:
                    errorTests.add(new TestResult(result));
                    break;
            }
        }

        notifyListenersResult(new TestRunResult(successfullTests, failedTests, errorTests));
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
     * Notifies all added listener for test progress.
     */
    private void notifyListenersProgress(TestContextResult result) {
        TestResult testresult = new TestResult(result);

        for (TestProgressListener listener : listeners) {
            listener.notifyProgress(testresult);
        }
    }

    /**
     * Notifies all added listeners for test result.
     */
    private void notifyListenersResult(TestRunResult result) {
        for (TestProgressListener listener : listeners) {
            listener.notifyResult(result);
        }
    }

    ///
}