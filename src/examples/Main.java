package examples;

import static main.jesting.framework.Check.*;

import main.jesting.framework.Test;
import main.jesting.framework.TestRunner;

public class Main {

    public static void main(String[] args) {
        TestRunner runner = new TestRunner();
        runner.addTest(Main.class);
        runner.run();
    }

    @Test
    public void testMethod1() {
        //checkFalse(1 != 2, "checkFalse(1 != 2)");

        checkEquals(1, 1, "Test");
    }

}