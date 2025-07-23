package phongth.example;

import java.util.logging.Logger;

public class NullPointerExample {

    private static final Logger logger = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = "Hello";

        if (!text.isEmpty()) {
            logger.info("Text is not empty");
        } else {
            logger.warning("Text is empty");
        }
    }
}