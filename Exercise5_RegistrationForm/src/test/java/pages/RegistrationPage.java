package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderMaleRadio = By.xpath("//label[text()='Male']");
    private By genderFemaleRadio = By.xpath("//label[text()='Female']");
    private By genderOtherRadio = By.xpath("//label[text()='Other']");
    private By phoneField = By.id("userNumber");
    private By dobField = By.id("dateOfBirthInput");
    private By submitButton = By.id("submit");
    private By successMsg = By.id("example-modal-sizes-title-lg");

    public void navigate() {
        driver.get("https://demoqa.com/automation-practice-form");

        // Ẩn quảng cáo, footer... để không che submit button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('footer').style.display='none';");
        js.executeScript("document.getElementById('fixedban').style.display='none';");
    }

    public void fillForm(String firstName, String lastName, String email, String gender, String phone, String dob) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        switch (gender.toLowerCase()) {
            case "male" -> {
                WebElement male = driver.findElement(genderMaleRadio);
                js.executeScript("arguments[0].click();", male);
            }
            case "female" -> {
                WebElement female = driver.findElement(genderFemaleRadio);
                js.executeScript("arguments[0].click();", female);
            }
            case "other" -> {
                WebElement other = driver.findElement(genderOtherRadio);
                js.executeScript("arguments[0].click();", other);
            }
        }

        driver.findElement(phoneField).sendKeys(phone);

        WebElement dobInput = driver.findElement(dobField);
        dobInput.click();
        dobInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), dob);
        dobInput.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        WebElement submitBtn = driver.findElement(submitButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submitBtn);
    }

    public String getSuccessMessage() {
        try {
            // Thay 'example-modal-something' bằng selector thực tế của popup thông báo (ví dụ id hoặc class của modal)
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-content"))).getText();
        } catch (TimeoutException e) {
            return ""; // Không thấy popup => coi như không thành công
        }
    }
}
