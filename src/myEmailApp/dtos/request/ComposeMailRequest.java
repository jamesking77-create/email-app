package myEmailApp.dtos.request;


import lombok.Data;
import lombok.ToString;
import myEmailApp.data.model.Mail;

import java.time.LocalDateTime;


@Data
@ToString
public class ComposeMailRequest {

 private LocalDateTime dateTime;
 private String senderEmail;
 private String recipientEmail;
 private Mail mail;




}
