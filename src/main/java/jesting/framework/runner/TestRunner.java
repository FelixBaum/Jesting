package jesting.framework.runner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import jesting.framework.annotations.Test;
import jesting.framework.context.TestContext;
import jesting.framework.result.TestContextResult;
import jesting.framework.result.TestContextResultType;
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
            
            testContext.setTimeout(annotation.timeout());

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

        ExecutorService executor = Executors.newFixedThreadPool(tests.size());
        List<TestFuture> futures = new ArrayList<TestFuture>();
        for (int i = 0; i < tests.size(); i++) {
            TestContext test = tests.get(i);
            Future<TestContextResult> future = executor.submit(new TestCallable(test));
            futures.add(new TestFuture(test, future));
        } 

        /// Parallel
        futures.parallelStream().forEach((future) -> {
            TestContextResult result = null;

            try {
                result = future.getFuture().get(future.getTest().getTimeout(), TimeUnit.MILLISECONDS);
            } catch (TimeoutException ex) {
                future.getFuture().cancel(true);

                result = new TestContextResult(future.getTest().getType().getSimpleName(), future.getTest().getNameOfTest(), TestContextResultType.ERROR);
                result.setTimeout(future.getTest().getTimeout());
                result.setIsTimedOut(true);
            } catch (ExecutionException ex) {
                future.getFuture().cancel(true);

                result = new TestContextResult(future.getTest().getType().getSimpleName(), future.getTest().getNameOfTest(), TestContextResultType.FAILURE);
                result.setFailureCause(ex);
            } catch (InterruptedException ex) {
                future.getFuture().cancel(true);

                result = new TestContextResult(future.getTest().getType().getSimpleName(), future.getTest().getNameOfTest(), TestContextResultType.FAILURE);
                result.setFailureCause(ex);
            }

            if (result != null) {
                notifyListenersProgress(result);
                results.add(result);
            }
        });
        ///

        executor.shutdownNow();

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