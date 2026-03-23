package com.jakob.patrol.service;

import com.jakob.patrol.model.Incident;
import com.jakob.patrol.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }


    public void recordIncident(String description) {
        Incident incident = new Incident();
        incident.setDescription(description);
        incident.setTime(LocalDateTime.now());
        incidentRepository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }
}
