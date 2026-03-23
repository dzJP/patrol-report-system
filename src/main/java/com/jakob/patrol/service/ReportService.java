package com.jakob.patrol.service;

import com.jakob.patrol.model.Incident;
import com.jakob.patrol.model.PatrolRound;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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