package TranHongPhong.example;

public class AccountService {
    public boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }

    public boolean registerAccount(String username, String password, String email) {
        if (username == null || password == null || email == null) return false;
        if (password.length() <= 6) return false;
        if (!isValidEmail(email)) return false;
        return true;
    }
}
