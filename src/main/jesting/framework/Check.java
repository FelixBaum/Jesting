package main.jesting.framework;

public class Check {

    /**
     * Static class
     */
    protected Check() {
    }

    /**
     * Fails a test with a message.
     * 
     * @param message the message wich will be shown
     */
    public static void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }

        throw new AssertionError(message);
    }

    /**
     * Fails a test with no message.
     */
    public static void fail() {
        fail(null);
    }

    /**
     * Checks a condition to be true.
     * 
     * @param condition condition to check
     * @param message message if it fails
     */
    public static void checkTrue(boolean condition, String message) {
        if (!condition) {
            fail(message);
        }
    }

    /**
     * Checks a condition to be true.
     * 
     * @param condition condition to check
     */
    public static void checkTrue(boolean condition) {
        checkTrue(condition, null);
    }

    /**
     * Checks a condition to be false.
     * 
     * @param condition condition to check
     */
    public static void checkFalse(boolean condition, String message) {
        checkTrue(!condition, message);
    }

    /**
     * Checks a condition to be false.
     * 
     * @param condition condition to check
     */
    public static void checkFalse(boolean condition) {
        checkFalse(condition, null);
    }

}