package com.jakob.patrol.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReport(String report) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("test@test.com");
        message.setSubject("Väktarrapport K.I. Flemingsberg");
        message.setText(report);
        mailSender.send(message);
    }
}
