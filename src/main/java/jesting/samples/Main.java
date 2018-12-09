package jesting.samples;

import static jesting.framework.Check.*;

import jesting.framework.Test;
import jesting.framework.listener.TestProgressListener;
import jesting.framework.runner.TestRunner;
import jesting.framework.result.TestResult;
import jesting.framework.result.TestRunResult;

public class Main implements TestProgressListener {

    public static void main(String[] args) {
        Main main = new Main();
        
        TestRunner runner = new TestRunner();
        runner.addTest(Main.class);
        runner.addProgressListener(main);
        runner.run();
    }

    public void notifyProgress(TestResult result) {
        result.printResultMessage(System.out);
    }

    public void notifyResult(TestRunResult result) {
    }

    @Test
    public void sampleTest() throws Exception {
        checkEquals(1, 2, "1 is not equal to 2");
    }

    @Test
    public void testMethod2() {
        checkFalse(1 == 2, "Test2");
    }

}