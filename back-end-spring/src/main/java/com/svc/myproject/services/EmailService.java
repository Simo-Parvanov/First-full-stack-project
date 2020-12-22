package com.svc.myproject.services;

import com.svc.myproject.domain.models.services.EmailResponseModel;
import com.svc.myproject.domain.models.services.EmailServiceModel;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    EmailResponseModel sendEmail(String to, String subject, String text);
}
