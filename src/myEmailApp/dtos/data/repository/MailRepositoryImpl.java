package myEmailApp.dtos.data.repository;

import myEmailApp.dtos.data.model.Mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MailRepositoryImpl implements MailRepository{

    private final List<Mail> mailList = new ArrayList<>();
    private int count;
    @Override
    public Mail saveMail(Mail mail) {
        boolean mailIsNotSaved = mail.getId() == 0;
        if (mailIsNotSaved){
            saveNewMail(mail);
            mailList.add(mail);
            count++;
        }
        return mail;
    }

    private void saveNewMail(Mail mail) {
        mail.setId(generateId());
    }

    private int generateId() {
        return count + 1;
    }

    @Override
    public Mail findById(int id) {
        for (Mail mail: mailList) {
            if (id == mail.getId()) return mail;
        }
        return null;
    }

    @Override
    public List<Mail> findAllMail() {
        return mailList;
    }

    @Override
    public void deleteMailById(int id) {
        for (Mail mail: mailList) {
            if (id == mail.getId()){
                mailList.remove(mail);
                count--;
                break;
            }
        }
    }

    @Override
    public long countMail() {
        return count;
    }

    @Override
    public void deleteAllMail() {
        mailList.clear();
        count = 0;
    }

    @Override
    public void deleteMailByTitle(String title) {
        mailList.removeIf(mail -> Objects.equals(title, mail.getSubject()));
    }

    @Override
    public Mail findByTitle(String title) {
        for (Mail mail: mailList) {
            if (title.equals(mail.getSubject())) return mail;
        }
        return null;
    }
}
