package examples;

import static main.jesting.framework.Check.*;

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
    }

}