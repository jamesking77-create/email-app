import controller.UserMailController;
import data.model.Mail;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;

import javax.swing.*;
import java.util.InputMismatchException;

public class Main {
    private static UserMailController mailController = new UserMailController();
    private  static LoginRequest loginRequest = new LoginRequest();

    private static  RegisterRequest registerRequest = new RegisterRequest();
    public static void main(String[] args) {
        openEmail();
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

    private static void logIn(){
        try{

            String emailAddress = input("Enter your email-address: ");
            String password = input("Enter your password: ");
            loginRequest.setEmailAddress(emailAddress);
            loginRequest.setPassword(password);
            mailController.login(loginRequest);
            mainMenu();
        }catch (NullPointerException e){
            display(e.getMessage());
          openEmail();
        }
    }


    private static void signUp() {
        try{

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
            mailController.registerUser(registerRequest);
            mainMenu();
        }catch (IllegalArgumentException e){
            display(e.getMessage());
            openEmail();
        }
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
    }

    private static void viewDraft() {
    }

    private static void viewOutBox() {
    }

    private static void viewSentBox() {

    }

    private static void viewInbox() {
        try{
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
           mailController.sendMail(loginRequest.getEmailAddress(),mail,receiverEmail);
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