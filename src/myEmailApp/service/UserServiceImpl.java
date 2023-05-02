package myEmailApp.service;
import myEmailApp.data.model.Mail;
import myEmailApp.data.model.User;
import myEmailApp.data.repository.UserRepository;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.response.ComposeMailResponse;
import myEmailApp.dtos.response.FindLoginResponse;
import myEmailApp.dtos.response.FindUserResponse;
import myEmailApp.util.Mapper;
import myEmailApp.exceptions.UnregisteredUserException;
import myEmailApp.exceptions.WrongInfoError;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    private  UserRepository userRepository;
    private final MailService mailService = new MailServiceImpl();
    @Override
    public FindUserResponse register(RegisterRequest registerRequest) throws WrongInfoError {
        FindUserResponse userResponse  = new FindUserResponse();
            validateEmailAddress(registerRequest.getEmailAddress());
            validatePhoneNumber(registerRequest.getPhoneNumber());
            validatePassword(registerRequest.getPassword());
        if (userExist(registerRequest.getEmailAddress())){
            generateEmailAddress(registerRequest);
            throw new WrongInfoError("Address " + registerRequest.getEmailAddress()+" already taken");
        }
        userRepository.save(Mapper.map(registerRequest));
        User foundUser = userRepository.findUserByEmailAddress(registerRequest.getEmailAddress());
        Mapper.map(foundUser,userResponse);
        return userResponse;
    }

    private void validatePhoneNumber(String phoneNumber) throws WrongInfoError {
        String [] alphabet = {"a","b","c","d","e","f", "g","m","h","i","j","k","l",
                "m","n","o","p","q","r","s","t", "u","v","w","x","y","z","A","B",
                "C","D","E", "F","G","H","I","J","K","L","M","N","O",
                "P","Q","R","S","T","U","V","W","X","Y","Z"};
        String [] negativeNumbers = {"-0", "-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9"};

        for (String letter : alphabet) {
            if (phoneNumber.contains(letter) || phoneNumber.length() != 11) {
                throw new WrongInfoError("invalid number");
            }
        }
        for (String digit:negativeNumbers) {
            if (phoneNumber.contains(digit)) {
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
        for (User user: userRepository.findAll()) {
            if (loginRequest.getEmailAddress().equals(user.getEmailAddress()) && loginRequest.getPassword().equals(user.getPassword())){
                Mapper.map(loginRequest, loginResponse);
                return loginResponse.getMessage();
            }
        }
           throw new UnregisteredUserException("Invalid info ->> ( user not registered )");

    }

    private void validatePassword(String password) throws WrongInfoError {
        if (!password.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")){
            throw  new WrongInfoError("invalid ->> ( password )");
        }
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(String.valueOf(id));
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
            return toString(inbox);
    }

    @Override
    public String viewAllMail(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> allMail = userRepository.findUserByEmailAddress(emailAddress).getAllMail();
            return toString(allMail);

    }

    @Override
    public String viewSentBox(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> sentBox = userRepository.findUserByEmailAddress(emailAddress).getSentBox();
        return toString(sentBox);

    }

    @Override
    public String viewOutBox(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> outBox = userRepository.findUserByEmailAddress(emailAddress).getOutBox();
        return toString(outBox);

    }

    @Override public String toString(List<Mail> x){
        for (Mail mail: x) {
            if (x.size() > 1) return "title: " + mail.getSubject() + "\nbody: " + mail.getBody();
        }
       return "title: " + x.get(0).getSubject() + "\nbody: " + x.get(0).getBody();
    }

    @Override
    public String viewTrash(String emailAddress) throws WrongInfoError {
        validateEmailAddress(emailAddress);
        List<Mail> trash = userRepository.findUserByEmailAddress(emailAddress).getTrash();
        return toString(trash);

    }

    @Override
    public String deleteMailById(String id, String userEmail) throws WrongInfoError {
            validateEmailAddress(userEmail);

//           userRepository.findUserByEmailAddress(userEmail).getTrash().add(mailService.findById(id));
           userRepository.findUserByEmailAddress(userEmail).getOutBox().removeIf(mails -> Objects.equals(id, mails.getId()));
           userRepository.findUserByEmailAddress(userEmail).getInbox().removeIf(mails -> Objects.equals(id, mails.getId()));
           userRepository.findUserByEmailAddress(userEmail).getAllMail().removeIf(mails -> Objects.equals(id, mails.getId()));
            return "(Mail) ->> deleted successfully";
    }

    @Override
    public String deleteMail(String mailSubject, String userEmail) throws WrongInfoError {
        validateEmailAddress(userEmail);
        Mail mail = mailService.findMailByTitle(mailSubject);
        userRepository.findUserByEmailAddress(userEmail).getTrash().add(mail);
        userRepository.findUserByEmailAddress(userEmail).getInbox().removeIf(mails -> mailSubject.equals(mails.getSubject()));
         userRepository.findUserByEmailAddress(userEmail).getAllMail().removeIf(mails -> mailSubject.equals(mails.getSubject()));
         userRepository.findUserByEmailAddress(userEmail).getOutBox().removeIf(mails -> mailSubject.equals(mails.getSubject()));
        return "(Mail) ->> deleted successfully";
    }

    @Override
    public String deleteAllMail(String userEmail) throws WrongInfoError {
        validateEmailAddress(userEmail);
        List<Mail> allMail = userRepository.findUserByEmailAddress(userEmail).getAllMail();
        userRepository.findUserByEmailAddress(userEmail).getTrash().addAll(allMail);
        userRepository.findUserByEmailAddress(userEmail).getInbox().clear();
        userRepository.findUserByEmailAddress(userEmail).getAllMail().clear();
        userRepository.findUserByEmailAddress(userEmail).getOutBox().clear();
        return "(All Mail) ->> deleted successfully";
    }

    @Override
    public String restoreMail(String userEmail, String id) throws WrongInfoError {
     validateEmailAddress(userEmail);
     List<Mail> trash = userRepository.findUserByEmailAddress(userEmail).getTrash();
     List<Mail> inbox = userRepository.findUserByEmailAddress(userEmail).getInbox();
     List<Mail> allMail = userRepository.findUserByEmailAddress(userEmail).getAllMail();
        for (Mail mail: trash) {
            if (Objects.equals(id, mail.getId())){
                inbox.add(mail);
                allMail.add(mail);
            }
        }

        return "Mail ->> ( Restored - successfully )";
    }
}
