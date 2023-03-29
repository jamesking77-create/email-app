package service;

import data.model.Mail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceImplTest {
    MailService mailService;
    Mail mail;
    @BeforeEach public void setUp(){
        mailService = new MailServiceImpl();
        mail = new Mail();
        mail.setSubject("goat");
        mail.setBody("cake");

    }
    @Test public void MailCanBeSavedTest(){
        Mail saveMail = mailService.saveMail(mail);
        assertEquals(1, saveMail.getId());
    }

}