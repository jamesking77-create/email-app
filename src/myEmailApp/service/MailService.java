package myEmailApp.service;

import myEmailApp.data.model.Mail;

import java.util.Optional;

public interface MailService {
    Mail saveMail(Mail composeMailRequest);
    void deleteMailById(String  id);
    void deleteAllMail();
    void deleteMailByTitle(String title);

    Mail findMailByTitle(String title);

    Optional<Mail> findById(String id);
}
