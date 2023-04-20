package myEmailApp.dtos.request;


import myEmailApp.data.model.Mail;

import java.time.LocalDateTime;

public class ComposeMailRequest {

 private LocalDateTime dateTime;
 private String senderEmail;
 private String recipientEmail;
 private Mail mail;

    @Override
    public String toString() {
        return "ComposeMailRequest{" +
                "dateTime=" + dateTime +
                ", senderEmail='" + senderEmail + '\'' +
                ", recipientEmail='" + recipientEmail + '\'' +
                ", mail=" + mail +
                '}';
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }



    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }


}
