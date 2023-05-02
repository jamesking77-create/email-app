package myEmailApp.service;

import myEmailApp.data.model.Mail;
import myEmailApp.data.model.User;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.response.FindUserResponse;
import myEmailApp.exceptions.UnregisteredUserException;
import myEmailApp.exceptions.WrongInfoError;

import java.util.List;
import java.util.Optional;

public interface UserService {
    FindUserResponse register(RegisterRequest registerRequest) throws WrongInfoError;
    String login(LoginRequest loginRequest) throws WrongInfoError, UnregisteredUserException;
    Optional<User> findUserById(String id);
    User findUserByEmailAddress(String emailAddress);
    String viewInbox(String emailAddress) throws WrongInfoError;
    String sendMail(ComposeMailRequest mailRequest) throws WrongInfoError;
    String viewAllMail(String emailAddress) throws WrongInfoError;

    String viewSentBox(String emailAddress) throws WrongInfoError;

    String viewOutBox(String emailAddress) throws WrongInfoError;
    String toString(List<Mail> x);

    String viewTrash(String emailAddress) throws WrongInfoError;

    String deleteMailById(String  id, String userEmail) throws WrongInfoError;

    String deleteMail(String mailSubject, String userEmail) throws WrongInfoError;

    String deleteAllMail(String userEmail) throws WrongInfoError;

    String restoreMail(String userEMail, String  id) throws WrongInfoError;
}
