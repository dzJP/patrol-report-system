package com.jakob.patrol.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${report.email.recipient}")
    private String recipient;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReport(String reportContent) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject("Patrol Report");
        message.setText(reportContent);

        mailSender.send(message);
        System.out.println("Sending patrol report email...");
    }
}