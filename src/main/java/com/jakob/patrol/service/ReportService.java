package com.jakob.patrol.service;

import com.jakob.patrol.model.Incident;
import com.jakob.patrol.model.PatrolRound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final PatrolService patrolService;
    private final IncidentService incidentService;

    public ReportService(PatrolService patrolService, IncidentService incidentService) {
        this.patrolService = patrolService;
        this.incidentService = incidentService;
    }

    public String generateReport() {
        List<PatrolRound> rounds = patrolService.getAllRounds();
        List<Incident> incidents = incidentService.getAllIncidents();

        StringBuilder report = new StringBuilder();

        report.append("Väktarrapport K.I. Flemingsberg\n\n");
        report.append("Rondering: \n");

        for(PatrolRound r : rounds) {
            report.append(r.getTime())
                    .append(" - ")
                    .append(r.getLocation())
                    .append("\n");
        }

        report.append("\nIncidenter:\n");

        if (incidents.isEmpty()) {
            report.append("Utan anmärkning\n");

        } else {
            for (Incident i : incidents) {
                report.append(i.getTime())
                        .append("-")
                        .append(i.getDescription())
                        .append("\n");
            }
        }

        return report.toString();
    }
}
