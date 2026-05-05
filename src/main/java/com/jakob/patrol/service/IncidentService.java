package com.jakob.patrol.service;

import com.jakob.patrol.model.*;
import com.jakob.patrol.repository.IncidentRepository;
import com.jakob.patrol.repository.PatrolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final PatrolService patrolService;

    public IncidentService(IncidentRepository incidentRepository,
                           PatrolService patrolService) {
        this.incidentRepository = incidentRepository;
        this.patrolService = patrolService;
    }

    @Transactional
    public void recordIncident(Long patrolId, String description) {

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Incident description cannot be empty");
        }

        Patrol patrol = patrolService.getActivePatrol(patrolId);

        Incident incident = new Incident();
        incident.setDescription(description.trim());
        incident.setTime(LocalDateTime.now());
        incident.setPatrol(patrol);

        incidentRepository.save(incident);
    }
}