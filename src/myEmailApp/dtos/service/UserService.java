package myEmailApp.dtos.service;

import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.data.model.User;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.util.UnregisteredUserException;
import myEmailApp.dtos.util.WrongInfoError;

public interface UserService {
    User register(RegisterRequest registerRequest) throws WrongInfoError;
    String login(LoginRequest loginRequest) throws WrongInfoError, UnregisteredUserException;
    User findUserById(int id);
    User findUserByEmailAddress(String emailAddress);
    String viewInbox(String emailAddress) throws WrongInfoError;
    String sendMail(ComposeMailRequest mailRequest) throws WrongInfoError;
    String viewAllMail(String emailAddress) throws WrongInfoError;

    String viewSentBox(String emailAddress) throws WrongInfoError;

    String viewOutBox(String emailAddress) throws WrongInfoError;

    String toString(Mail x);

    String viewTrash(String emailAddress) throws WrongInfoError;

    String deleteMail(int id, String userEmail) throws WrongInfoError;

    String deleteMail(String mailSubject, String userEmail);

    String deleteAllMail(String mailSubject, String userEmail);

}
