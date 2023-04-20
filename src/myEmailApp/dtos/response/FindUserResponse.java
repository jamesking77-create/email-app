package myEmailApp.dtos.response;

import myEmailApp.data.model.Mail;

import java.util.ArrayList;
import java.util.List;

public class FindUserResponse {
    private String firstName;
    private String  lastName;
    private String phoneNumber;
    private String  emailAddress;

    private int id;
    private List<Mail> inbox = new ArrayList<>();
    private List<Mail> outBox = new ArrayList<>();
    private List<Mail> sentBox = new ArrayList<>();
    private List<Mail> draft = new ArrayList<>();
    private List<Mail> allMail = new ArrayList<>();
    private List<Mail> trash = new ArrayList<>();

    @Override
    public String toString() {
        return "FindUserResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", id=" + id +
                ", inbox=" + inbox +
                ", outBox=" + outBox +
                ", sentBox=" + sentBox +
                ", draft=" + draft +
                ", allMail=" + allMail +
                ", trash=" + trash +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(List<Mail> inbox) {
        this.inbox = inbox;
    }

    public List<Mail> getOutBox() {
        return outBox;
    }

    public void setOutBox(List<Mail> outBox) {
        this.outBox = outBox;
    }

    public List<Mail> getSentBox() {
        return sentBox;
    }

    public void setSentBox(List<Mail> sentBox) {
        this.sentBox = sentBox;
    }

    public List<Mail> getDraft() {
        return draft;
    }

    public void setDraft(List<Mail> draft) {
        this.draft = draft;
    }

    public List<Mail> getAllMail() {
        return allMail;
    }

    public void setAllMail(List<Mail> allMail) {
        this.allMail = allMail;
    }

    public List<Mail> getTrash() {
        return trash;
    }

    public void setTrash(List<Mail> trash) {
        this.trash = trash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
