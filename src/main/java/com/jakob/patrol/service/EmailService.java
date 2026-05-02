package com.jakob.patrol.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Value("${report.email.recipient}")
    private String reportRecipient;
    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendReport(String report) {

        System.out.println("=== PATROL REPORT ===");
        System.out.println(report);
        System.out.println("=====================");
        /*SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(reportRecipient);
        message.setSubject("Patrol Report");
        message.setText(report);
        mailSender.send(message);*/
    }
}
