package examples;

import static main.jesting.framework.Check.*;

import main.jesting.framework.Test;
import main.jesting.framework.TestRunner;

public class Main {

    public static void main(String[] args) {
        try {
            checkTrue(1 == 2, "checkTrue (1 == 2)");
        } catch (Throwable ex) {
            System.err.println(ex.getMessage());
        }

        try {
            checkFalse(1 != 2, "checkFalse (1 != 2)");
        } catch (Throwable ex) {
            System.err.println(ex.getMessage());
        }

        TestRunner runner = new TestRunner();
        runner.addTest(Main.class);
        runner.run();
    }

    @Test
    public void testMethod() {
        checkFalse(1 != 2, "checkFalse (1 != 2)");
    }

}