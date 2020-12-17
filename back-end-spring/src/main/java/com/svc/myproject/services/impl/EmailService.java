package com.svc.myproject.services.impl;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    public void sendEmail();
    public void sendEmailWithAttachment() throws MessagingException, IOException;
}
