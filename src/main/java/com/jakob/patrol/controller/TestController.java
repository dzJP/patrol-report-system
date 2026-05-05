package com.jakob.patrol.controller;

import com.jakob.patrol.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final EmailService emailService;

    public TestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test-mail")
    public String testMail() {
        emailService.sendReport("Test email working");
        return "sent";
    }
}