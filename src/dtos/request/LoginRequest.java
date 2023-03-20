package dtos.request;

public class LoginRequest {
    private String emailAddress;
    private String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String userName) {
        this.emailAddress = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
