package com.flight.reser.util;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
    public void sendEmailWithAttachment(String to, String subject, String text, String attachmentFileName, byte[] attachment) throws MessagingException;
}
