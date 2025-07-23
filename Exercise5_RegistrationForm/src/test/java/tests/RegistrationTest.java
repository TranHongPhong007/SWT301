package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Tests - demoqa.com")
public class RegistrationTest extends BaseTest {
    static RegistrationPage registrationPage;

    @BeforeAll
    static void initPage() {
        registrationPage = new   RegistrationPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Submit registration form with hardcoded data")
    void testRegistrationSingleCase() {
        registrationPage.navigate();
        registrationPage.fillForm("Nguyen", "An", "ngan@example.com", "Female", "0912345678", "22 Jul 2025");
        registrationPage.submitForm();

        String message = registrationPage.getSuccessMessage();
        assertTrue(message.contains("Thanks for submitting the form"));
    }

    @ParameterizedTest(name = "Register with: {0} {1}, {2}, {3}, {4}, {5} => expected={6}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @Order(2)
    @DisplayName("Submit registration form with CSV data")
    void testRegisterWithCsv(String firstName, String lastName, String email, String gender, String phone, String dob, String expected) {
        registrationPage.navigate();
        registrationPage.fillForm(firstName.trim(), lastName.trim(), email.trim(), gender.trim(), phone.trim(), dob.trim());
        registrationPage.submitForm();

        String message = registrationPage.getSuccessMessage();  // Trả về "" nếu không có dialog hoặc không có message
        System.out.println("✅ Success message returned: " + message);

        if ("success".equalsIgnoreCase(expected)) {
            assertTrue(message.contains("Thanks for submitting the form"), "❌ Expected success but no success message.");
        } else {
            assertFalse(message.contains("Thanks for submitting the form"), "❌ Expected failure but success message appeared.");
        }
    }

    @AfterAll
    static void cleanup() {
        driver.quit();
    }
}
