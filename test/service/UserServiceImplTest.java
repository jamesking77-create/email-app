package service;

import data.model.Mail;
import data.model.User;
import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;
    RegisterRequest registerRequest;
    LoginRequest loginRequest;
    RegisterRequest registerRequest2;
    Mail mail;
    Mail mail2;
    @BeforeEach public void setUp(){
        mail = new Mail();
        mail2 = new Mail();
        userService = new UserServiceImpl();
        registerRequest = new RegisterRequest();
        registerRequest2 = new RegisterRequest();
        loginRequest = new LoginRequest();
        registerRequest.setFirstName("moyinoluwa");
        registerRequest.setLastName("micheal");
        registerRequest.setUserName("moyex");
        registerRequest.setPassword("6789");
        registerRequest.setEmailAddress("moyex33@gmail.com");
        loginRequest.setEmailAddress(registerRequest.getEmailAddress());
        loginRequest.setPassword(registerRequest.getPassword());
        registerRequest2.setFirstName("moyin");
        registerRequest2.setLastName("micheal");
        registerRequest2.setEmailAddress("moyex23@gmail.com");

        mail.setSubject("i love cake");
        mail.setBody("buy me cake");
        mail2.setSubject("goat");
        mail2.setBody("body");


    }
    @Test  public void registerOneUser_userIdIsOneTest(){
       User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
    }

    @Test public void loginUserWithoutRegistrationThrowsExceptionTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        String loginUser = userService.login(loginRequest);
        assertEquals("moyex33@gmail.com WELCOME" , loginUser);

    }
    @Test public void findUserByIdTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User foundUser = userService.findUserById(savedUser.getId());
        assertEquals(savedUser, foundUser);
    }
    @Test public void findUserByEmailAddressTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User foundUser = userService.findUserByEmailAddress(savedUser.getEmailAddress());
        assertEquals(savedUser, foundUser);
    }
    @Test public void registeringWithExistingEmailAddress_generatesNewAddressTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());

    }
    @Test public void userCanSendMailTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(),mail,savedUser2.getEmailAddress());
        assertEquals(1, savedUser2.getInbox().size());
    }

    @Test public void userSendsTwoMails_InboxOfRecipientSizeIsTwoTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(),mail,savedUser2.getEmailAddress());
        assertEquals(1, savedUser2.getInbox().size());
        userService.sendMail(savedUser.getEmailAddress(),mail2,savedUser2.getEmailAddress());
        assertEquals(2, savedUser2.getInbox().size());

    }
    @Test public void userSendMail_MailReflectInSentBoxTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(), mail ,savedUser2.getEmailAddress());
        assertEquals(1, savedUser2.getInbox().size());
        assertEquals(1, savedUser.getSentBox().size());
    }

    @Test public void userSendMail_MailReflectInAllMailBoxTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(), mail ,savedUser2.getEmailAddress());
        assertEquals(1, savedUser2.getInbox().size());
        assertEquals(1, savedUser.getAllMail().size());
    }
    @Test public void userSendMailWithWrongAddress_MailReflectInOutBoxTest(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        registerRequest2.setEmailAddress("jamesgmail.com");
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(), mail ,savedUser2.getEmailAddress());
        assertEquals(1, savedUser.getOutBox().size());
    }

    @Test public void userCanDeleteMailFromAllBox_InboxSizeIsMinusOne(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(), mail2 ,savedUser.getEmailAddress());
        assertEquals(1, savedUser.getInbox().size());
        userService.deleteAllMail(savedUser.getEmailAddress(), mail2.getSubject());
        assertEquals(0, savedUser.getInbox().size());

    }

    @Test public void userCanDeleteMailFromInbox_InboxSizeIsMinusOne(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(), mail2 ,savedUser.getEmailAddress());
        assertEquals(1, savedUser.getInbox().size());
        userService.deleteMail(mail2.getSubject(),savedUser.getEmailAddress());
        assertEquals(0, savedUser.getInbox().size());
    }

    @Test public void userCanDeleteMaiByIdlFromInbox_InboxSizeIsMinusOne(){
        User savedUser =  userService.register(registerRequest);
        assertEquals(1, savedUser.getId());
        User savedUser2 =  userService.register(registerRequest2);
        assertEquals(2, savedUser2.getId());
        userService.sendMail(savedUser.getEmailAddress(), mail2 ,savedUser.getEmailAddress());
        assertEquals(1, savedUser.getInbox().size());
        userService.deleteMail(mail2.getId(), savedUser.getEmailAddress());
        assertEquals(0, savedUser.getInbox().size());
    }
}