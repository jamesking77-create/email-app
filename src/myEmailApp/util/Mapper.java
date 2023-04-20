package myEmailApp.util;

import myEmailApp.data.model.Mail;
import myEmailApp.data.model.User;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.response.FindLoginResponse;
import myEmailApp.dtos.response.FindUserResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterRequest registerRequest) {
        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmailAddress(registerRequest.getEmailAddress());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(registerRequest.getPassword());
        return user;
    }

    public static void map (User foundUser, FindUserResponse userResponse){
        userResponse.setFirstName(foundUser.getFirstName());
        userResponse.setLastName(foundUser.getLastName());
        userResponse.setEmailAddress(foundUser.getEmailAddress());
        userResponse.setPhoneNumber(foundUser.getPhoneNumber());
        userResponse.setId(foundUser.getId());
        userResponse.setInbox(foundUser.getInbox());
        userResponse.setSentBox(foundUser.getSentBox());
        userResponse.setAllMail(foundUser.getAllMail());
        userResponse.setDraft(foundUser.getDraft());
        userResponse.setOutBox(foundUser.getOutBox());
        userResponse.setTrash(foundUser.getTrash());


    }

    public static void map(LoginRequest loginRequest, FindLoginResponse loginResponse) {
        loginResponse.setEmailAddress(loginRequest.getEmailAddress());
        loginResponse.setMessage(loginRequest.getEmailAddress() + " WELCOME BACK");
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
