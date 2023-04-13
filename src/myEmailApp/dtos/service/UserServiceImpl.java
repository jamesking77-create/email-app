package myEmailApp.dtos.service;
import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.data.model.User;
import myEmailApp.dtos.data.repository.UserRepository;
import myEmailApp.dtos.data.repository.UserRepositoryImpl;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.response.ComposeMailResponse;
import myEmailApp.dtos.response.FindLoginResponse;
import myEmailApp.dtos.util.Mapper;
import myEmailApp.dtos.util.UnregisteredUserException;
import myEmailApp.dtos.util.WrongInfoError;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;


public class UserServiceImpl implements UserService{
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final MailService mailService = new MailServiceImpl();
    @Override
    public User register(RegisterRequest registerRequest) throws WrongInfoError {
            validateEmailAddress(registerRequest.getEmailAddress());
            validatePhoneNumber(registerRequest.getPhoneNumber());
            validatePassword(registerRequest.getPassword());
        if (userExist(registerRequest.getEmailAddress())){
            System.err.println("Address " + registerRequest.getEmailAddress()+" already taken");
            generateEmailAddress(registerRequest);
        }
        return userRepository.saveUser(Mapper.map(registerRequest));
    }

    private void validatePhoneNumber(String phoneNumber) throws WrongInfoError {
        String [] alphabet = {"a","b","c","d","e","f",
                "g","m","h","i","j","k","l",
                "m","n","o","p","q","r","s","t",
                "u","v","w","x","y","z","A","B","C","D","E",
                "F","G","H","I","J","K","L","M","N","O",
                "P","Q","R","S","T","U","V","W","X","Y","Z"};
        for (String letter : alphabet) {
            if (phoneNumber.contains(letter)) {
                throw new WrongInfoError("invalid number");
            }
        }
    }

    private void generateEmailAddress(RegisterRequest registerRequest) throws WrongInfoError {
        validateEmailAddress(registerRequest.getEmailAddress());
        SecureRandom random = new SecureRandom();
        int randNum = random.nextInt(200);
         registerRequest.setEmailAddress(registerRequest.getFirstName().charAt(0)+
                registerRequest.getLastName()+ randNum+"@gmail.com");
         if (registerRequest.getFirstName().charAt(0) == registerRequest.getLastName().charAt(0))
             registerRequest.setEmailAddress(registerRequest.getFirstName().charAt(0)+"_"+
                     registerRequest.getLastName()+ randNum+"@gmail.com");

        System.out.println(registerRequest.getEmailAddress());
    }

    private void validateEmailAddress(String emailAddress) throws WrongInfoError {
        if (!emailAddress.matches("[a-z]+(_)*[a-z]*[0-9]*@[a-z]+\\.[a-z]{2,}")){
            throw new WrongInfoError("invalid email address");
        }
    }

    private boolean userExist(String emailAddress) {
        User foundUser = userRepository.findUserByEmailAddress(emailAddress);
        return foundUser != null;
    }

    @Override
    public String login(LoginRequest loginRequest) throws  UnregisteredUserException {
        FindLoginResponse loginResponse = new FindLoginResponse();
            if (userExist(loginRequest.getEmailAddress())){
                Mapper.map(loginRequest, loginResponse);
                return loginResponse.getMessage();
            }
           throw new UnregisteredUserException("Invalid info ->> ( user not registered )");

    }

    private void validatePassword(String password) throws WrongInfoError {
        if (!password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")){
            throw  new WrongInfoError("invalid password");
        }
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }

  
              
    @Override
    public String sendMail(ComposeMailRequest mailRequest) throws WrongInfoError {
        validateEmail(mailRequest.getRecipientEmail(), mailRequest.getSenderEmail(), mailRequest.getMail());
        ComposeMailResponse mailResponse = new ComposeMailResponse();
        User sender = userRepository.findUserByEmailAddress(mailRequest.getSenderEmail());
        User receiver = userRepository.findUserByEmailAddress(mailRequest.getRecipientEmail());

        if (Objects.equals(mailRequest.getRecipientEmail(), receiver.getEmailAddress()) &&
                Objects.equals(sender.getEmailAddress(), mailRequest.getSenderEmail())){

            mailService.saveMail(mailRequest.getMail());
            receiver.getInbox().add(mailRequest.getMail());
            receiver.getAllMail().add(mailRequest.getMail());
            sender.getAllMail().add(mailRequest.getMail());
            sender.getSentBox().add(mailRequest.getMail());
            return mailResponse.getMessage();
        }

        throw  new WrongInfoError("mail not sent (recipient email address) ->> not found");
    }

    private void validateEmail(String recipientEMailAddress, String senderEmailAddress, Mail mail) throws WrongInfoError {
        if (!recipientEMailAddress.matches("[a-z]+(_)*[a-z]*[0-9]*@[a-z]+\\.[a-z]{2,}")){
            User sender = userRepository.findUserByEmailAddress(senderEmailAddress);
            sender.getOutBox().add(mail);
            throw new WrongInfoError("Mail not sent ->> ( invalid email-address ) ");


        }
    }


    @Override
    public String viewInbox(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> inbox = userRepository.findUserByEmailAddress(emailAddress).getInbox();
        for (Mail mail : inbox) {
            return toString(mail);
        }
        return null;
    }

    @Override
    public String viewAllMail(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> allMail = userRepository.findUserByEmailAddress(emailAddress).getAllMail();
        for (Mail mail : allMail ) {
            return toString(mail);
        }
        return null;
    }

    @Override
    public String viewSentBox(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> sentBox = userRepository.findUserByEmailAddress(emailAddress).getSentBox();
        for (Mail mail : sentBox ) {
            return toString(mail);
        }
        return null;
    }

    @Override
    public String viewOutBox(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> outBox = userRepository.findUserByEmailAddress(emailAddress).getOutBox();
        for (Mail mail : outBox ) {
            return toString(mail);
        }
        return null;
    }

    @Override public String toString(Mail x){
        return "title: " + x.getSubject() + "\nbody: " + x.getBody();
    }

    @Override
    public String viewTrash(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> trash = userRepository.findUserByEmailAddress(emailAddress).getTrash();
        for (Mail mail : trash ) {
            return toString(mail);
        }
        return null;
    }

    @Override
    public String deleteMail(int id, String userEmail) {
        List<Mail> mailList = userRepository.findUserByEmailAddress(userEmail).getAllMail();
        for (Mail mail:mailList ) {
            if (id == mail.getId()) {
                userRepository.findUserByEmailAddress(userEmail).getTrash().add(mail);
            }
        }
           userRepository.findUserByEmailAddress(userEmail).getOutBox().removeIf(mails -> id == mails.getId());
           userRepository.findUserByEmailAddress(userEmail).getInbox().removeIf(mails -> id == mails.getId());
           userRepository.findUserByEmailAddress(userEmail).getAllMail().removeIf(mails -> id == mails.getId());
            return "(Mail) ->> deleted";
    }

    @Override
    public String deleteMail(String mailSubject, String userEmail){
        List<Mail> trash = userRepository.findUserByEmailAddress(userEmail).getTrash();
        trash.add(mailService.findMailByTitle(mailSubject));
        mailService.deleteMailByTitle(mailSubject);
        Mail mail = mailService.findMailByTitle(mailSubject);
        userRepository.findUserByEmailAddress(userEmail).getTrash().add(mail);
        userRepository.findUserByEmailAddress(userEmail).getInbox().removeIf(mails -> mailSubject.equals(mails.getSubject()));
         userRepository.findUserByEmailAddress(userEmail).getAllMail().removeIf(mails -> mailSubject.equals(mails.getSubject()));
         userRepository.findUserByEmailAddress(userEmail).getOutBox().removeIf(mails -> mailSubject.equals(mails.getSubject()));
        return "(Mail) ->> deleted";
    }

    @Override
    public String deleteAllMail(String userEmail, String mailSubject) {
        userRepository.findUserByEmailAddress(userEmail).getInbox().clear();
        userRepository.findUserByEmailAddress(userEmail).getAllMail().clear();
        userRepository.findUserByEmailAddress(userEmail).getOutBox().clear();
        return "(All Mail) ->> deleted";
    }
}
