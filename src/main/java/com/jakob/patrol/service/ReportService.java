package com.jakob.patrol.service;

import com.jakob.patrol.dto.ReportResponse;
import com.jakob.patrol.model.Incident;
import com.jakob.patrol.model.PatrolRound;
import com.jakob.patrol.repository.IncidentRepository;
import com.jakob.patrol.repository.PatrolRoundRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {

    private final PatrolRoundRepository roundRepository;
    private final IncidentRepository incidentRepository;

    public ReportService(PatrolRoundRepository roundRepository,
                         IncidentRepository incidentRepository) {
        this.roundRepository = roundRepository;
        this.incidentRepository = incidentRepository;
    }

    public String generateReportForPatrol(Long patrolId) {

        List<PatrolRound> rounds = roundRepository.findByPatrolId(patrolId);
        List<Incident> incidents = incidentRepository.findByPatrolId(patrolId);

        StringBuilder report = new StringBuilder();

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter timeFormatter =
                DateTimeFormatter.ofPattern("HH:mm");

        report.append("Väktarrapport K.I. Flemingsberg\n")
                .append(LocalDate.now().format(dateFormatter))
                .append("\n\n");

        report.append("Rondering:\n");

        for (PatrolRound r : rounds) {
            report.append(r.getTime().format(timeFormatter))
                    .append(" - ")
                    .append(r.getLocation())
                    .append("\n");
        }

        report.append("\nIncidenter:\n");

        if (incidents.isEmpty()) {
            report.append("Utan anmärkning\n");
        } else {
            for (Incident i : incidents) {
                report.append(i.getTime().format(timeFormatter))
                        .append(" - ")
                        .append(i.getDescription())
                        .append("\n");
            }
        }

        return report.toString();
    }
}