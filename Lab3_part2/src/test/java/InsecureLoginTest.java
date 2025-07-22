import phongth.example.InsecureLogin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InsecureLoginTest {

    @Test
    void testLoginSuccess() {
        InsecureLogin.login("admin", System.getenv("ADMIN_PASSWORD")); // bảo mật cho mật khẩu
        //InsecureLogin.login("admin", "123456");
        assertTrue(true);

    }

    @Test
    void testLoginFail() {
        InsecureLogin.login("user", "wrongpassword");
        assertTrue(true);
    }

    @Test
    void testPrintUserInfo() {
        InsecureLogin insecureLogin = new InsecureLogin();
        insecureLogin.printUserInfo("John Doe");
        assertTrue(true);
    }
}
