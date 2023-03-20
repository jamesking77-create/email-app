package service;

import data.model.Mail;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface MailService {
    String composeMail(String recipientEmail ,String subject, String body);
    Mail[]inbox();
    List<Mail> allMail();
    List<Mail> sentBox();
    List<Mail> draft(Mail mail);
    List<Mail> outBox(Mail mail);
    String delete();
    String deleteAll();



}
