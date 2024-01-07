package com.example.book_your_show.service;

public interface MailConfigurationService {
    public void updateSenderEmail(String senderEmail);
    public void mailSender(String senderEmail, String recipientEmail, String body, String subject);
}
