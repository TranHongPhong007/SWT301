package phongth.example;

import java.io.*;
import java.util.logging.Logger;

public class PathTraversalExample {
    private static final Logger logger = Logger.getLogger(PathTraversalExample.class.getName());

    public static void main(String[] args) throws IOException {
        String userInput = "../secret.txt";
        File baseDir = new File("safe_directory"); // thư mục an toàn được phép truy cập

        File file = new File(baseDir, userInput);

        String canonicalBasePath = baseDir.getCanonicalPath();
        String canonicalFilePath = file.getCanonicalPath();

        if (!canonicalFilePath.startsWith(canonicalBasePath)) {
            logger.warning("Access to file is not allowed: Path traversal detected");
            return;
        }

        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            logger.info("Reading file: " + file.getPath());
            reader.close();
        }
    }
}
