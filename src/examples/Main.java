package examples;

import static main.jesting.framework.Check.*;

import main.jesting.framework.Test;
import main.jesting.framework.TestProgressListener;
import main.jesting.framework.TestRunner;
import main.jesting.framework.result.TestResult;

public class Main implements TestProgressListener {

    public static void main(String[] args) {
        Main main = new Main();

        TestRunner runner = new TestRunner();
        runner.addTest(Main.class);
        runner.addListener(main);
        runner.run();
    }

    public void notifyProgress(TestResult result) {
        result.printResultMessage(System.out);
    }

    public void notifyResult() {

    }

    @Test
    public void sampleTest() {
        checkEquals(1, 2, "1 is not equal to 2");
    }

    @Test
    public void testMethod2() {
        checkFalse(1 == 2, "Test2");
    }

}