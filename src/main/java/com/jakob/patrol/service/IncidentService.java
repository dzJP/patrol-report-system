package com.jakob.patrol.service;

import com.jakob.patrol.model.*;
import com.jakob.patrol.repository.IncidentRepository;
import com.jakob.patrol.repository.PatrolRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final PatrolRepository patrolRepository;

    public IncidentService(IncidentRepository incidentRepository,
                           PatrolRepository patrolRepository) {
        this.incidentRepository = incidentRepository;
        this.patrolRepository = patrolRepository;
    }

    public void recordIncident(Long patrolId, String description) {

        Patrol patrol = patrolRepository.findById(patrolId)
                .orElseThrow(() -> new RuntimeException("Patrol not found"));

        if (patrol.getStatus() != PatrolStatus.ACTIVE) {
            throw new IllegalStateException("Cannot add incident to inactive patrol");
        }

        Incident incident = new Incident();
        incident.setDescription(description);
        incident.setTime(LocalDateTime.now());
        incident.setPatrol(patrol);

        incidentRepository.save(incident);
    }
}