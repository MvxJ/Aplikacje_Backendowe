package com.mailer.mailer.Service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Getter
@Setter
public class MailerService {
    private String from;
    private String to;
    private String host;
    private String password;

    public void send(String subject, String userMessage) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(userMessage);
            System.out.println("sending email to " + to);
            Transport.send(message);
            System.out.println("successfully send email to " + to);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
