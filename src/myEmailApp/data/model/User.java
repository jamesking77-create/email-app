package myEmailApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Data
@Document
public class User {
    private String firstName;
    private String  lastName;
    private String phoneNumber;
    @Id
    private String id;
    private String emailAddress;
    private String password;
    @DBRef
    private List<Mail> inbox = new ArrayList<>();
    @DBRef
    private List<Mail> outBox = new ArrayList<>();
    @DBRef
    private List<Mail> sentBox = new ArrayList<>();
    @DBRef
    private List<Mail> draft = new ArrayList<>();
    @DBRef
    private List<Mail> allMail = new ArrayList<>();
    @DBRef
    private List<Mail> trash = new ArrayList<>();


}
