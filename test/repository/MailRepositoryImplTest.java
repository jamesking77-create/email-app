package repository;

import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.data.repository.MailRepository;
import myEmailApp.dtos.data.repository.MailRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailRepositoryImplTest {
    private MailRepository mailRepository;
    private Mail mail;
    @BeforeEach public void setUp(){
        mail = new Mail();
        mailRepository = new MailRepositoryImpl();
        mail.setSubject("food");
        mail.setBody("i send you mail");
    }

    @Test public void saveOneMail_idOfMailIsOneTest(){
      Mail savedMail =  mailRepository.saveMail(mail);
      assertEquals(1, savedMail.getId());
    }

    @Test public void saveOneMail_countIsOneTest(){
        Mail savedMail =  mailRepository.saveMail(mail);
        assertEquals(1, mailRepository.countMail());
    }
    @Test public void saveOneMail_deleteMailByIdTest(){
        Mail savedMail =  mailRepository.saveMail(mail);
        assertEquals(1, mailRepository.countMail());
        mailRepository.deleteMailById(1);
        assertEquals(0, mailRepository.countMail());
    }
    @Test public void saveOneMail_findMailByIdTest(){
        Mail savedMail =  mailRepository.saveMail(mail);
        assertEquals(1, mailRepository.countMail());
        Mail foundMail = mailRepository.findById(1);
        assertEquals(savedMail, foundMail);
    }

    @Test public void saveOneMail_findAllMailTest(){
        Mail savedMail =  mailRepository.saveMail(mail);
        assertEquals(1, mailRepository.countMail());
        mailRepository.findAllMail();
        assertEquals(1, mailRepository.findAllMail().size());
    }

    @Test public void saveOneMail_deleteAllMailTest(){
        Mail savedMail =  mailRepository.saveMail(mail);
        assertEquals(1, mailRepository.countMail());
        mailRepository.deleteAllMail();
        assertEquals(0, mailRepository.countMail());
    }


}