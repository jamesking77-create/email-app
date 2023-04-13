package myEmailApp.dtos.service;

import myEmailApp.dtos.data.model.Mail;
import myEmailApp.dtos.data.repository.MailRepository;
import myEmailApp.dtos.data.repository.MailRepositoryImpl;


public class MailServiceImpl implements MailService{
    MailRepository mailRepository = new MailRepositoryImpl();
    @Override
    public Mail saveMail(Mail MailRequest) {
        return mailRepository.saveMail(MailRequest);
    }

    @Override
    public void deleteMailById(int id){
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
