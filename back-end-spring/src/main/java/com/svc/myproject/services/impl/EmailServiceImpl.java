package com.svc.myproject.services.impl;

import com.svc.myproject.domain.models.services.EmailResponseModel;
import com.svc.myproject.domain.models.services.EmailServiceModel;
import com.svc.myproject.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public EmailResponseModel sendEmail(String to, String subject, String text) {
        EmailResponseModel responseModel = new EmailResponseModel();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Order placed by PS SHOP PRO №" + subject);
            message.setText(text);

            emailSender.send(message);
            responseModel.setMessage("Email send ok!");

        } catch (Exception ex) {
            responseModel.setMessage("Error send email: " + ex.getMessage());
        }

        return responseModel;
    }
}
