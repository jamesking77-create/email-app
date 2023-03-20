package service;

import data.model.Mail;

import java.util.List;

public class MailServiceImpl implements MailService{
    @Override
    public String composeMail(String recipientEmail, String subject, String body) {
        return null;
    }

    @Override
    public Mail[] inbox() {
        return new Mail[0];
    }

    @Override
    public List<Mail> allMail() {
        return null;
    }

    @Override
    public List<Mail> sentBox() {
        return null;
    }

    @Override
    public List<Mail> draft(Mail mail) {
        return null;
    }

    @Override
    public List<Mail> outBox(Mail mail) {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String deleteAll() {
        return null;
    }
}
