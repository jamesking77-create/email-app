package myEmailApp.dtos.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindLoginResponse {
    private String emailAddress;
    private String message;


}
