package phongth.example;

import java.io.*;
import java.util.logging.Logger;

public class ResourceLeakExample {

    private static final Logger logger = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        String fileName = "data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        } catch (IOException e) {
            logger.severe("Error reading file: " + e.getMessage());
        }
    }
}