package controller;

import data.model.Mail;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import service.UserService;
import service.UserServiceImpl;

public class UserMailController {
private final UserService userService = new UserServiceImpl();
    public Object registerUser(RegisterRequest registerRequest){
        try {
           return userService.register(registerRequest);
        }catch (IllegalArgumentException e){
             return e.getMessage();
        }

    }
    public Object login(LoginRequest loginRequest){
        try {
            return userService.login(loginRequest);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public Object sendMail(String senderEmail, Mail mail, String  receiverEmail){
        try {
            return userService.sendMail(senderEmail, mail, receiverEmail);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public Object viewInbox(String userEmail){
        try {
            return userService.viewInbox(userEmail);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public Object viewOutBox(String userEmail){
        try {
            return userService.viewOutBox(userEmail);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }

    public Object viewAllBox(String userEmail){
        try {
            return userService.viewAllMail(userEmail);
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
}
