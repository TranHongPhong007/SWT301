package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private final WebDriverWait wait;

    private final By userNameField = By.id("Username");
    private final By passwordField = By.id("Password");
    private final By loginButton = By.xpath("//button[text()='Login']");
    private final By successMsg = By.xpath("//*[contains(text(),'Logout')]");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    public void navigate() {
        driver.get("http://localhost:8080/tourify/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameField));
    }

    public void login(String userName, String password) {
        type(userNameField, userName);
        type(passwordField, password);
        click(loginButton);
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
        return driver.findElement(successMsg).getText();
    }
}
