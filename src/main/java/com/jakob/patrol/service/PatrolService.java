package com.jakob.patrol.service;

import com.jakob.patrol.dto.PatrolResponse;
import com.jakob.patrol.dto.ReportResponse;
import com.jakob.patrol.model.*;
import com.jakob.patrol.repository.IncidentRepository;
import com.jakob.patrol.repository.PatrolRepository;
import com.jakob.patrol.repository.PatrolRoundRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatrolService {

    private final PatrolRepository patrolRepository;
    private final PatrolRoundRepository roundRepository;
    private final IncidentRepository incidentRepository;
    private final ReportService reportService;
    private final EmailService emailService;

    public PatrolService(PatrolRepository patrolRepository,
                         PatrolRoundRepository roundRepository,
                         IncidentRepository incidentRepository,
                         ReportService reportService,
                         EmailService emailService) {
        this.patrolRepository = patrolRepository;
        this.roundRepository = roundRepository;
        this.incidentRepository = incidentRepository;
        this.reportService = reportService;
        this.emailService = emailService;
    }

    public Long startPatrol(String username) {
        Patrol patrol = new Patrol();
        patrol.setUsername(username);
        patrol.setStartTime(LocalDateTime.now());
        patrol.setStatus(PatrolStatus.ACTIVE);

        return patrolRepository.save(patrol).getId();
    }

    public void recordRound(Long patrolId, String location) {
        Patrol patrol = getActivePatrol(patrolId);

        PatrolRound round = new PatrolRound();
        round.setLocation(location);
        round.setTime(LocalDateTime.now());
        round.setPatrol(patrol);

        roundRepository.save(round);
    }

    public void endPatrol(Long patrolId) {
        Patrol patrol = getActivePatrol(patrolId);

        patrol.setStatus(PatrolStatus.COMPLETED);
        patrol.setEndTime(LocalDateTime.now());

        patrolRepository.save(patrol);

        String report = reportService.generateReportForPatrol(patrolId);
        emailService.sendReport(report);
    }

    public Patrol getActivePatrol(Long patrolId) {
        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found"));

        if (patrol.getStatus() != PatrolStatus.ACTIVE) {
            throw new IllegalStateException("Patrol is not active");
        }

        return patrol;
    }

    public PatrolResponse getPatrol(Long patrolId) {
        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found"));

        PatrolResponse response = new PatrolResponse();
        response.setId(patrol.getId());
        response.setUsername(patrol.getUsername());
        response.setStatus(patrol.getStatus().name());
        response.setStartTime(patrol.getStartTime());
        response.setEndTime(patrol.getEndTime());

        return response;
    }

    public ReportResponse getReport(Long patrolId) {

        List<PatrolRound> rounds = roundRepository.findByPatrolId(patrolId);
        List<Incident> incidents = incidentRepository.findByPatrolId(patrolId);

        ReportResponse response = new ReportResponse();
        response.setPatrolId(patrolId);
        response.setTotalRounds(rounds.size());
        response.setTotalIncidents(incidents.size());

        List<String> summaries = incidents.stream()
                .map(i -> i.getDescription())
                .toList();

        response.setIncidentSummaries(summaries);

        return response;
    }
}