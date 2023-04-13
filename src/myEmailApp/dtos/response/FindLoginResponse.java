package myEmailApp.dtos.response;

public class FindLoginResponse {
    private String emailAddress;
    private String message;

    @Override
    public String toString() {
        return "FindLoginResponse{" +
                "userName='" + emailAddress + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
