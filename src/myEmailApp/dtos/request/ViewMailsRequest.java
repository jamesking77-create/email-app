package myEmailApp.dtos.request;

public class ViewMailsRequest {
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "ViewMailsRequest{" +
                "userEmail='" + userEmail + '\'' +
                '}';
    }


}
