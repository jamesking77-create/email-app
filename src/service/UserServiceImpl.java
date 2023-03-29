package service;
import data.model.Mail;
import data.model.User;
import data.repository.UserRepository;
import data.repository.UserRepositoryImpl;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.response.ComposeMailResponse;
import dtos.response.FindLoginResponse;
import util.Mapper;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;


public class UserServiceImpl implements UserService{
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final MailService mailService = new MailServiceImpl();
    @Override
    public User register(RegisterRequest registerRequest) {
            validateEmailAddress(registerRequest.getEmailAddress());
            validatePhoneNumber(registerRequest.getPhoneNumber());
        if (userExist(registerRequest.getEmailAddress())){
            System.err.println("Address " + registerRequest.getEmailAddress()+" already taken");
            generateEmailAddress(registerRequest);
        }
        return userRepository.saveUser(Mapper.map(registerRequest));
    }

    private void validatePhoneNumber(String phoneNumber){
        String [] alphabet = {"a","b","c","d","e","f",
                "g","m","h","i","j","k","l",
                "m","n","o","p","q","r","s","t",
                "u","v","w","x","y","z","A","B","C","D","E",
                "F","G","H","I","J","K","L","M","N","O",
                "P","Q","R","S","T","U","V","W","X","Y","Z"};
        for (String letter : alphabet) {
            if (phoneNumber.contains(letter)) {
                throw new IllegalArgumentException("invalid number");
            }
        }
    }

    private void generateEmailAddress(RegisterRequest registerRequest) {
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

    private void validateEmailAddress(String emailAddress) {
        if (!emailAddress.matches("[a-z]+[0-9]+@[a-z]+\\.[a-z]{2,}")){
            System.err.println("invalid email");
        }
    }

    private boolean userExist(String emailAddress) {
        User foundUser = userRepository.findUserByEmailAddress(emailAddress);
        return foundUser != null;
    }

    @Override
    public String login(LoginRequest loginRequest){
        FindLoginResponse loginResponse = new FindLoginResponse();
        boolean userHasRegistered = loginRequest.getEmailAddress().equals(userRepository.findUserByEmailAddress(loginRequest.getEmailAddress()).getEmailAddress());
    if (userHasRegistered) {
        Mapper.map(loginRequest, loginResponse);
        return loginResponse.getMessage();

    }
    return "you do not have an account";

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
    public String viewInbox(String  userEmail) {

        for (Mail mail :  userRepository.findUserByEmailAddress(userEmail).getInbox()) {
            if (userRepository.findUserByEmailAddress(userEmail).getInbox().size() > 0){
                return String.format("""
                 ===========================
                 %s
                 ===========================
                 """,mail);
            }

        }
        return null;
    }
              
    @Override
    public String sendMail(String senderEmailAddress, Mail mail, String recipientEMailAddress) {
        validateEmail(recipientEMailAddress, senderEmailAddress, mail);
        ComposeMailResponse mailResponse = new ComposeMailResponse();
        User sender = userRepository.findUserByEmailAddress(senderEmailAddress);
        User receiver = userRepository.findUserByEmailAddress(recipientEMailAddress);

        if (Objects.equals(recipientEMailAddress, receiver.getEmailAddress()) &&
                Objects.equals(sender.getEmailAddress(), senderEmailAddress)){

            mailService.saveMail(mail);
            receiver.getInbox().add(mail);
            receiver.getAllMail().add(mail);
            sender.getAllMail().add(mail);
            sender.getSentBox().add(mail);
            return mailResponse.getMessage();
        }

        return "mail not sent (recipient email address) ->> not found";
    }

    private void validateEmail(String recipientEMailAddress, String senderEmailAddress, Mail mail) {
        if (!recipientEMailAddress.matches("[a-z]+[0-9]+@[a-z]+\\.[a-z]{2,}")){
            User sender = userRepository.findUserByEmailAddress(senderEmailAddress);
            sender.getOutBox().add(mail);
            System.err.println("invalid email address");


        }
    }

    @Override
    public List<Mail> viewAllMail(String userEmail) {
       return userRepository.findUserByEmailAddress(userEmail).getAllMail();
    }

    @Override
    public List<Mail> viewSentBox(String userEmail) {
        return userRepository.findUserByEmailAddress(userEmail).getSentBox();
    }

    @Override
    public List<Mail> viewOutBox(String userEmail) {
        return userRepository.findUserByEmailAddress(userEmail).getOutBox();
    }

    @Override
    public String deleteMail(int id, String userEmail) {
       mailService.deleteMailById(id);
       userRepository.findUserByEmailAddress(userEmail).getOutBox().removeIf(mails -> id == mails.getId());
       userRepository.findUserByEmailAddress(userEmail).getInbox().removeIf(mails -> id == mails.getId());
       userRepository.findUserByEmailAddress(userEmail).getAllMail().removeIf(mails -> id == mails.getId());
        return "(Mail) ->> deleted";
    }

    @Override
    public String deleteMail(String mailSubject, String userEmail){
        mailService.deleteMailByTitle(mailSubject);
        Mail mail = mailService.findMailByTitle(mailSubject);
        userRepository.findUserByEmailAddress(userEmail).getInbox().removeIf(mails -> mailSubject.equals(mails.getSubject()));
         userRepository.findUserByEmailAddress(userEmail).getAllMail().removeIf(mails -> mailSubject.equals(mails.getSubject()));
         userRepository.findUserByEmailAddress(userEmail).getOutBox().removeIf(mails -> mailSubject.equals(mails.getSubject()));
        userRepository.findUserByEmailAddress(userEmail).getTrash().add(mail);
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
