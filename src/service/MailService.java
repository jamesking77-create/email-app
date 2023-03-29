package service;

import data.model.Mail;

public interface MailService {
    Mail saveMail(Mail composeMailRequest);
    void deleteMailById(int id);
    void deleteAllMail();
    void deleteMailByTitle(String title);

    Mail findMailByTitle(String title);

    Mail findById(int id);
}
