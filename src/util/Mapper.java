package util;

import data.model.Mail;
import data.model.User;
import dtos.request.ComposeMailRequest;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.response.FindLoginResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmailAddress(registerRequest.getEmailAddress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        return user;
    }

    public static void map(LoginRequest loginRequest, FindLoginResponse loginResponse) {
        loginResponse.setEmailAddress(loginRequest.getEmailAddress());
        loginResponse.setMessage(loginRequest.getEmailAddress() + " WELCOME");
    }

    public static Mail map(ComposeMailRequest composeMailRequest) {
        Mail mail = new Mail();
        mail.setSubject(composeMailRequest.getSubject());
        mail.setBody(composeMailRequest.getBody());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy, hh:mm a");
        mail.setDateTime(LocalDateTime.parse(formatter.format(LocalDateTime.now())));
        return mail;
    }
}
