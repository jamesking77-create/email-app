package myEmailApp.service;

import myEmailApp.data.model.Mail;
import myEmailApp.data.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MailServiceImpl implements MailService {
    @Autowired
    MailRepository mailRepository;

    @Override
    public Mail saveMail(Mail MailRequest) {
        return mailRepository.save(MailRequest);
    }

    @Override
    public void deleteMailById(String  id) {
        mailRepository.deleteById(String.valueOf(id));
    }

    @Override
    public void deleteAllMail() {
        mailRepository.deleteAll();
    }

    @Override
    public void deleteMailByTitle(String title) {
        mailRepository.deleteMailByTitle(title);
    }

    @Override
    public Mail findMailByTitle(String title) {
        return null;
    }

    @Override
    public Optional<Mail> findById(String id) {
        return mailRepository.findById(id);
    }



}
