package com.svc.myproject.services.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService{

private final JavaMailSender emailSender;

    public EmailServiceImpl(@Qualifier("getJavaMailSender") JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pc.shop.gamer.pro@gmail.com");
        message.setTo("simo_hariev@abv.bg");
        message.setSubject("subject");
        message.setText(text);
        emailSender.send(message);
    }

    @Override
   public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("simo_hariev@abv.bg");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        emailSender.send(msg);

    }

    @Override
   public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = emailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("simo_hariev@abv.bg");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        emailSender.send(msg);

    }
}
