package phongth.example;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLInjectionExample {

    private static final Logger logger = Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";  // giả lập input tấn công
        String query = "SELECT username FROM users WHERE username = ?";

        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userInput);
            logger.info("Executing query safely with PreparedStatement");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Found user: " + rs.getString("username"));
                }
            }

        } catch (SQLException e) {
            logger.severe("Database error: " + e.getMessage());
        }
    }
}