package com.jakob.patrol.controller;

import com.jakob.patrol.service.EmailService;
import com.jakob.patrol.service.IncidentService;
import com.jakob.patrol.service.PatrolService;
import com.jakob.patrol.service.ReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patrol")
public class PatrolController {

    private final PatrolService patrolService;
    private final IncidentService incidentService;
    private final ReportService reportService;
    private final EmailService emailService;


    public PatrolController(PatrolService patrolService, IncidentService incidentService, ReportService reportService, EmailService emailService) {
        this.patrolService = patrolService;
        this.incidentService = incidentService;
        this.reportService = reportService;
        this.emailService = emailService;
    }

    @PostMapping("/end-shift")
    public void endShift() {
        String report = reportService.generateReport();
        emailService.sendReport(report);
    }

    @PostMapping("/round")
    public void addRound(@RequestParam String location) {
        patrolService.recordRound(location);
    }

    @PostMapping("incident")
    public void addIncident(@RequestParam String description) {
        incidentService.recordIncident(description);
    }

}
