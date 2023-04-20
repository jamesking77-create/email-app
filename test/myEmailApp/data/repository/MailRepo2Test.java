package myEmailApp.data.repository;

import myEmailApp.data.model.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailRepo2Test {

    @Autowired
    MailRepo2 mailRepo2;

    @Test void test(){
        Mail mail = new Mail();
        mailRepo2.save(mail);
        assertEquals(1, mailRepo2.count());
    }

}