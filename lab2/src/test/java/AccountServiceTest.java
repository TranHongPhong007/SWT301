import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Test;
import TranHongPhong.example.AccountService;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    AccountService service = new AccountService();
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testRegisterAccount(String username, String password, String email, boolean expected) {
        boolean actual = service.registerAccount(username, password, email);
        assertEquals(expected, actual);
    }
    @Test
    void testNullInputs() {
        // Một hoặc nhiều giá trị là null → phải trả về false
        assertFalse(service.registerAccount(null, "password", "email@example.com"));
        assertFalse(service.registerAccount("user", null, "email@example.com"));
        assertFalse(service.registerAccount("user", "password", null));
        assertFalse(service.registerAccount(null, null, null));
    }
}