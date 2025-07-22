package phongth.example;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {

    private static final Logger logger = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        String s = "Hello";

        try {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Length: " + s.length());
            }
        } catch (NullPointerException e) {
            logger.severe("NullPointerException occurred: " + e.getMessage());
        }
    }
}