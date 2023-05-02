package myEmailApp.dtos.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {
    private String emailAddress;
    private String password;


}
