package service;

import data.model.Mail;
import data.model.User;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;

import java.util.List;

public interface UserService {
    User register(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
    User findUserById(int id);
    User findUserByEmailAddress(String emailAddress);
    List<Mail> viewInbox(String userEmail);
    String sendMail(String senderEMailAddress, Mail mail, String recipientEMailAddress);
    List<Mail> viewAllMail(String userEmail);
    List<Mail> viewSentBox(String userEmail);

    List<Mail> viewOutBox(String userEmail);

    String deleteMail(int id, String userEmail);

    String deleteMail(String mailSubject, String userEmail);

    String deleteAllMail(String mailSubject, String userEmail);

}
