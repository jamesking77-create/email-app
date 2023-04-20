package myEmailApp.service;

import myEmailApp.data.model.Mail;
import myEmailApp.data.repository.MailRepo2;
import myEmailApp.data.repository.MailRepository;
import myEmailApp.data.repository.MailRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;


public class MailServiceImpl implements MailService {
    MailRepository mailRepository = new MailRepositoryImpl();

    @Autowired
    MailRepo2 mailRepo2;

    @Override
    public Mail saveMail(Mail MailRequest) {
        return mailRepository.saveMail(MailRequest);
    }

    @Override
    public void deleteMailById(int id) {
        mailRepository.deleteMailById(id);
    }

    @Override
    public void deleteAllMail() {
        mailRepository.deleteAllMail();
    }

    @Override
    public void deleteMailByTitle(String title) {
        mailRepository.deleteMailByTitle(title);
    }

    @Override
    public Mail findMailByTitle(String title) {
        return mailRepository.findByTitle(title);
    }

    @Override
    public Mail findById(int id) {
        return mailRepository.findById(id);
    }
}
