package phongth.example;

import java.util.logging.Logger;

public final class Constants {
    public static final int MAX_USERS = 100;

    private Constants() {
        // Ngăn không cho khởi tạo class này
    }
}

public class InterfaceFieldModificationExample {

    private static final Logger logger = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        logger.info("MAX_USERS: " + Constants.MAX_USERS);
    }
}