package util;

import data.model.User;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.response.FindLoginResponse;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmailAddress(registerRequest.getEmailAddress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setUserName(registerRequest.getUserName());
        return user;
    }

    public static void map(LoginRequest loginRequest, FindLoginResponse loginResponse) {
        loginResponse.setEmailAddress(loginRequest.getEmailAddress());
        loginResponse.setMessage(loginRequest.getEmailAddress() + " WELCOME");
    }
}
