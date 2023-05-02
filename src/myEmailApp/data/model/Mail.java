package myEmailApp.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document
public class Mail {
    private String subject;
    private String body;
    @Id
    private String  id;
    private String  userId;
    private LocalDateTime dateTime;



}
