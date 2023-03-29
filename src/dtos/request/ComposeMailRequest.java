package dtos.request;



public class ComposeMailRequest {
 private String recipientEmail;
 private String subject;
 private String body;

    @Override
    public String toString() {
        return "ComposeMailRequest{" +
                "recipientEmail='" + recipientEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }


}
