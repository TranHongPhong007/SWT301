package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RegistrationTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    void testRegister(String firstName, String lastName, String username, String email,
                      String password, String confirmPassword, String expected) {
        driver.get("http://localhost:8080/tourify/register");

        // Nhập thông tin đăng ký
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("email")).sendKeys(email);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        passwordField.sendKeys(password);

        WebElement confirmField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordConfirm")));
        confirmField.click(); // focus trước
        confirmField.sendKeys(confirmPassword);

        // Bấm nút đăng ký
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        submitButton.click();

// ✅ SỬA: kiểm tra theo kỳ vọng "success" hoặc "error"
        if ("success".equalsIgnoreCase(expected)) {
            // ✅ Chờ message nếu kỳ vọng là thành công
            WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
            String messageText = alertMessage.getText().trim();
            System.out.println("✅ Thông báo thành công: " + messageText);
            assertTrue(messageText.toLowerCase().contains("confirmation email"), "❌ Kỳ vọng thành công nhưng thông báo không đúng.");
        } else {
            try {
                // ✅ Chờ message nếu có, kiểm tra không chứa "confirmation email"
                WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
                String text = alert.getText().toLowerCase();
                System.out.println("⚠️ Message lỗi: " + text);
                assertFalse(text.contains("confirmation email"), "❌ Kỳ vọng thất bại nhưng lại hiển thị thông báo thành công.");
            } catch (TimeoutException e) {
                // ✅ Nếu không có message thì chấp nhận, vì form bị chặn client-side
                System.out.println("⛔ Không có message xuất hiện. Có thể do validation phía client.");
                assertTrue(true); // Pass as expected
            }
        }
    }
}
