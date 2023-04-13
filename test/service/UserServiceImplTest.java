package service;

import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.data.model.User;
import myEmailApp.dtos.request.ComposeMailRequest;
import myEmailApp.dtos.request.LoginRequest;
import myEmailApp.dtos.request.RegisterRequest;
import myEmailApp.dtos.service.UserService;
import myEmailApp.dtos.service.UserServiceImpl;
import myEmailApp.dtos.util.UnregisteredUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import myEmailApp.dtos.util.WrongInfoError;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;
    RegisterRequest registerRequest;
    LoginRequest loginRequest;
    RegisterRequest registerRequest2;

    ComposeMailRequest composeMailRequest;

    ComposeMailRequest composeMailRequest2;
    Mail mail;
    Mail mail2;
    @BeforeEach public void setUp(){
        mail = new Mail();
        mail2 = new Mail();
        composeMailRequest = new ComposeMailRequest();
        composeMailRequest2 = new ComposeMailRequest();
        userService = new UserServiceImpl();
        registerRequest = new RegisterRequest();
        registerRequest2 = new RegisterRequest();
        loginRequest = new LoginRequest();

        registerRequest.setFirstName("moyinoluwa");
        registerRequest.setLastName("micheal");
        registerRequest.setUserName("moyex");
        registerRequest.setPassword("Kinglat77");
        registerRequest.setPhoneNumber("00000");
        registerRequest.setEmailAddress("moyex33@gmail.com");


        loginRequest.setEmailAddress("jam@gmail.com");
        loginRequest.setPassword(registerRequest.getPassword());


        registerRequest2.setFirstName("moyin");
        registerRequest2.setLastName("micheal");
        registerRequest2.setEmailAddress("moyex3@gmail.com");
        registerRequest2.setPhoneNumber("9999999");
        registerRequest2.setPassword("Moyin123");

        mail.setSubject("i love cake");
        mail.setBody("buy me cake");
        mail2.setSubject("goat");
        mail2.setBody("body");


        composeMailRequest.setMail(mail);
        composeMailRequest.setSenderEmail(registerRequest.getEmailAddress());
        composeMailRequest.setRecipientEmail(registerRequest2.getEmailAddress());

        composeMailRequest2.setMail(mail);
        composeMailRequest2.setSenderEmail(registerRequest.getEmailAddress());
        composeMailRequest2.setRecipientEmail("jamesgmail.com");



    }
    @Test  public void registerOneUser_userIdIsOneTest() throws WrongInfoError {
       User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
    }

    @Test public void loginUserWithoutRegistrationThrowsExceptionTest() {
        assertThrows(UnregisteredUserException.class, ()-> userService.login(loginRequest));

    }
    @Test public void findUserByIdTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User foundUser = userService.findUserById(savedUser.getId());
        assertEquals(savedUser, foundUser);
    }
    @Test public void findUserByEmailAddressTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User foundUser = userService.findUserByEmailAddress(savedUser.getEmailAddress());
        assertEquals(savedUser, foundUser);
    }
    @Test public void registeringWithExistingEmailAddress_generatesNewAddressTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());

    }
    @Test public void userCanSendMailTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
    }

    @Test public void userSendsTwoMails_InboxOfRecipientSizeIsTwoTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        userService.sendMail(composeMailRequest);
        assertEquals(2, savedUser2.getInbox().size());

    }
    @Test public void userSendMail_MailReflectInSentBoxTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        assertEquals(1, savedUser.getSentBox().size());
    }

    @Test public void userSendMail_MailReflectInAllMailBoxTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        assertEquals(1, savedUser.getAllMail().size());
    }
    @Test public void userSendMailWithWrongAddress_MailReflectInOutBoxTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        assertThrows(WrongInfoError.class, ()->  userService.sendMail(composeMailRequest2));
        assertEquals(1, savedUser.getOutBox().size());
    }

    @Test public void userCanDeleteMailFromAllBox_InboxSizeIsMinusOne() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        userService.deleteAllMail(savedUser.getEmailAddress(), mail.getSubject());
        assertEquals(0, savedUser.getInbox().size());

    }

    @Test public void userCanDeleteMailFromInbox_InboxSizeIsMinusOne() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        userService.deleteMail(mail.getSubject(),savedUser.getEmailAddress());
        assertEquals(0, savedUser.getInbox().size());
    }

    @Test public void userCanDeleteMaiByIdlFromInbox_InboxSizeIsMinusOne() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        userService.deleteMail(mail.getId(), savedUser.getEmailAddress());
        assertEquals(0, savedUser.getInbox().size());
    }

    @Test public  void deleteMailFromInbox_sizeOfTrashIsOneTest() throws WrongInfoError {
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(composeMailRequest);
        assertEquals(1, savedUser2.getInbox().size());
        userService.deleteMail(mail.getId(), savedUser.getEmailAddress());
        assertEquals(0, savedUser.getInbox().size());
        assertEquals(1, savedUser.getTrash().size());

    }
}