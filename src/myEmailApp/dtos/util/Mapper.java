package myEmailApp.dtos.util;

import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.data.model.User;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.response.FindLoginResponse;

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
        mail.setSubject(composeMailRequest.getMail().getSubject());
        mail.setBody(composeMailRequest.getMail().getBody() );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy, hh:mm a");
        mail.setDateTime(LocalDateTime.parse(formatter.format(LocalDateTime.now())));
        return mail;
    }
}
