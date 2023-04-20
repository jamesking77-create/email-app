package myEmailApp.data.repository;

import myEmailApp.data.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailRepo2 extends MongoRepository<Mail, Integer> {

//    Mail findByIdAndAndUserId(int id, int userId);

}
