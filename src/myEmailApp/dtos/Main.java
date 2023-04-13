package myEmailApp.dtos;

import myEmailApp.dtos.controller.UserMailController;
import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.request.ViewMailsRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;


@SpringBootApplication
public class Main {
    private static final UserMailController mailController = new UserMailController();
    private  static final LoginRequest loginRequest = new LoginRequest();

    private static final RegisterRequest registerRequest = new RegisterRequest();

    private static final ComposeMailRequest mailRequest = new ComposeMailRequest();

    private static final ViewMailsRequest viewMailRequest = new ViewMailsRequest();

    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
//        openEmail();
    }

    private static void openEmail(){
        String infoPage = input("""
                ===============================
                1: -> sign up
                2: -> log in
                ===============================
                """);
        switch (infoPage.charAt(0)){
            case '1' -> signUp();
            case '2' -> logIn();
            default -> openEmail();
        }
    }

    private static void logIn() {
        String emailAddress = input("Enter your email-address: ");
        String password = input("Enter your password: ");
        loginRequest.setEmailAddress(emailAddress);
        loginRequest.setPassword(password);
        display(mailController.login(loginRequest).toString());
        openEmail();
    }


    private static void signUp() {
        String firstName = input("Enter your first name: ");
        String lastName = input("Enter your last name: ");
        String emailAddress = input("Enter your desired email-address: ");
        String phoneNumber = input("Enter your phone number: ");
        String password = input("Enter your password: ");
        registerRequest.setFirstName(firstName);
        registerRequest.setLastName(lastName);
        registerRequest.setEmailAddress(emailAddress);
        registerRequest.setPassword(password);
        registerRequest.setPhoneNumber(phoneNumber);
        display( mailController.registerUser(registerRequest).toString());
        openEmail();
        mainMenu();
    }

    private static void mainMenu() {
        String mainMenu = input("""
                =============================
                         MY - EMAIL
                =============================
                -> COMPOSE MAIL
                -> INBOX
                -> SENT-BOX
                -> OUT-BOX
                -> DRAFT
                -> ALL-MAIL
                -> TRASH
                =============================
                """);
        switch (mainMenu.charAt(0)){
            case '1' -> composeMail();
            case '2' -> viewInbox();
            case '3' -> viewSentBox();
            case  '4' -> viewOutBox();
            case '5' -> viewDraft();
            case  '6' -> viewAllMail();
            case '7' -> viewTrash();
            default -> mainMenu();
        }
    }

    private static void viewTrash() {
    }

    private static void viewAllMail() {
        try{
            viewMailRequest.setUserEmail(registerRequest.getEmailAddress());
            mailController.viewAllBox(registerRequest.getEmailAddress());
        }catch (IllegalArgumentException e){
            display(e.getMessage());
        }
    }

    private static void viewDraft() {
    }

    private static void viewOutBox() {
        try{
            viewMailRequest.setUserEmail(registerRequest.getEmailAddress());
            mailController.viewOutBox(registerRequest.getEmailAddress());
        }catch (IllegalArgumentException e){
            display(e.getMessage());
        }
    }

    private static void viewSentBox() {


    }

    private static void viewInbox() {
        try{
            viewMailRequest.setUserEmail(registerRequest.getEmailAddress());
           display(mailController.viewInbox(registerRequest.getEmailAddress()));
           mainMenu();
        }catch (IllegalArgumentException e){
            display(e.getMessage());
        }
    }

    private static void composeMail() {
       try{
           String receiverEmail = input("To: ");
           String title = input("Subject: ");
           String body = input("body: ");
           Mail mail = new Mail();
           mail.setSubject(title);
           mail.setBody(body);
           mailRequest.setMail(mail);
           mailRequest.setSenderEmail(loginRequest.getEmailAddress());
           mailRequest.setRecipientEmail(receiverEmail);
           mailController.sendMail(mailRequest);
            mainMenu();
       }catch (IllegalArgumentException e){
           display(e.getMessage());
           mainMenu();
       }finally {
           mainMenu();
       }

    }


    private static String input(String prompt){
        return JOptionPane.showInputDialog(prompt);
    }

    private static void display(String prompt){
        JOptionPane.showMessageDialog(null, prompt);
    }
}