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
    public EmailResponseModel sendEmail(EmailServiceModel emailServiceModel) {
        EmailResponseModel responseModel = new EmailResponseModel();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailServiceModel.getTo());
            message.setSubject(emailServiceModel.getSubject());
            message.setText(emailServiceModel.getText());

            emailSender.send(message);
            responseModel.setMessage("Email send ok!");

        } catch (Exception ex) {
            responseModel.setMessage("Error send email: " + ex.getMessage());
        }

        return responseModel;
    }
}
