package myEmailApp.data.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String  lastName;
    private String phoneNumber;
    private int id;
    private String emailAddress;

    private String password;
    private List<Mail> inbox = new ArrayList<>();
    private List<Mail> outBox = new ArrayList<>();
    private List<Mail> sentBox = new ArrayList<>();
    private List<Mail> draft = new ArrayList<>();
    private List<Mail> allMail = new ArrayList<>();
    private List<Mail> trash = new ArrayList<>();

    public List<Mail> getTrash() {
        return trash;
    }

    public void setTrash(List<Mail> trash) {
        this.trash = trash;
    }

    public List<Mail> getAllMail() {
        return allMail;
    }

    public void setAllMail(List<Mail> allMail) {
        this.allMail = allMail;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
}
