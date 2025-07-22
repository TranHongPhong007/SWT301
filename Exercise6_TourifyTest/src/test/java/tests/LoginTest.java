package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    void testLogin(String username, String password, String expected) {
        driver.get("http://localhost:8080/tourify/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // Nếu có modal thì đóng nó
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal")));
            driver.findElement(By.cssSelector(".modal .btn-close")).click();
        } catch (Exception ignored) {}

        // In ra URL để kiểm tra nếu cần debug
        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL after login: " + currentUrl);

        boolean loggedIn = false;
        try {
            loggedIn = wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/landing"),
                    ExpectedConditions.urlContains("/dashboard"),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert-success")),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".toast-success")),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(".user-info"))
            )) != null;
        } catch (TimeoutException e) {
            loggedIn = false; // Hết thời gian mà không thấy dấu hiệu đăng nhập thành công
        }

        // Kiểm tra kết quả mong đợi
        if ("success".equalsIgnoreCase(expected)) {
            assertTrue(loggedIn, "❌ Kỳ vọng đăng nhập thành công nhưng không thành công.");
        } else {
            assertFalse(loggedIn, "❌ Kỳ vọng thất bại nhưng lại đăng nhập thành công.");
        }
    }
}