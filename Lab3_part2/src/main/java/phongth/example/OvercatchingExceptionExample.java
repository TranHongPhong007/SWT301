package phongth.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {

    private static final Logger logger = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = new int[5];

            logger.info("Accessing arr[10]...");

            int value = arr[10]; // sẽ gây lỗi tại đây

            logger.log(Level.INFO, "Value: {0}", value);

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Array index out of bounds: {0}", e.getMessage());
        }
    }
}