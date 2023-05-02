package myEmailApp.dtos.response;

import lombok.Data;
import lombok.ToString;
import myEmailApp.data.model.Mail;

import java.util.ArrayList;
import java.util.List;
@Data
@ToString
public class FindUserResponse {
    private String firstName;
    private String  lastName;
    private String phoneNumber;
    private String  emailAddress;
    private String  id;
    private List<Mail> inbox = new ArrayList<>();
    private List<Mail> outBox = new ArrayList<>();
    private List<Mail> sentBox = new ArrayList<>();
    private List<Mail> draft = new ArrayList<>();
    private List<Mail> allMail = new ArrayList<>();
    private List<Mail> trash = new ArrayList<>();


}
