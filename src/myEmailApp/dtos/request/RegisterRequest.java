package myEmailApp.dtos.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RegisterRequest {
    private String firstName;
    private String  lastName;
    private String phoneNumber;
    private int id;
    private String userName;
    private String password;
    private String emailAddress;


}
