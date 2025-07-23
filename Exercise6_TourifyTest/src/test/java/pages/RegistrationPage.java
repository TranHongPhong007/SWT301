package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {
    private final WebDriverWait wait;

    private final By firstNameField = By.id("First Name");
    private final By lastNameField = By.id("Last Name");
    private final By userNameField = By.id("Username");
    private final By emailField = By.id("Email address");
    private final By passwordField = By.id("Password");
    private final By confirmPasswordField = By.id("Confirm");
    private final By submitButton = By.xpath("//button[text()='Register']");
    private final By successMsg = By.xpath("//*[contains(text(),'Login here')]");

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    public void navigate() {
        driver.get("http://localhost:8080/tourify/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    public void fillForm(String firstName, String lastName, String userName,
                         String email, String password, String confirmPassword) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(userNameField, userName);
        type(emailField, email);
        type(passwordField, password);
        type(confirmPasswordField, confirmPassword);
    }

    public void submit() {
        click(submitButton);
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return driver.findElement(successMsg).getText();
    }
}
