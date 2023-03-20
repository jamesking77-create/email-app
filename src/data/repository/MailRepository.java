package data.repository;

import data.model.Mail;

import java.util.List;

public interface MailRepository {
    Mail saveMail(Mail mail);
    Mail findById(int id);
    List<Mail> findAllMail();
    void deleteMailById(int id);
    long countMail();
    void deleteAllMail();
}
