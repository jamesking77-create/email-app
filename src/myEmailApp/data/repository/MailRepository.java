package myEmailApp.data.repository;

import myEmailApp.data.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MailRepository extends MongoRepository<Mail, String > {

   default  void deleteMailByTitle(String title){
       findAll().removeIf(mail -> mail.getSubject().equals(title));
   }

}
