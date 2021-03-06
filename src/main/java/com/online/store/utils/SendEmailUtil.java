package com.online.store.utils;

import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailUtil {

    public static void sendCode(String email, String code, Double totalPrice) {

        final String username = "testuserma1488@gmail.com";
        final String password = "TestUser1488";
        final Logger logger = Logger.getLogger(SendEmailUtil.class);


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Confirm CODE");
            message.setText("Your order for: " + totalPrice + "UAH\n"
                    + "Your Confirm CODE: " + code);
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error in sending email via email util. ", e);
        }
    }
}
