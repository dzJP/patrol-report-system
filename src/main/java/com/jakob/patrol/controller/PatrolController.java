package com.jakob.patrol.controller;

import jakarta.validation.Valid;

import com.jakob.patrol.service.IncidentService;
import com.jakob.patrol.service.PatrolService;
import com.jakob.patrol.dto.*;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrols")
public class PatrolController {

    private final PatrolService patrolService;
    private final IncidentService incidentService;

    public PatrolController(PatrolService patrolService,
                            IncidentService incidentService) {
        this.patrolService = patrolService;
        this.incidentService = incidentService;
    }

    @PostMapping
    public Long startPatrol(Authentication authentication) {
        String username = authentication.getName();
        return patrolService.startPatrol(username);
    }

    @PostMapping("/{patrolId}/rounds")
    public void addRound(@PathVariable Long patrolId,
                         @Valid @RequestBody RoundRequest request) {
        patrolService.recordRound(patrolId, request.getLocation());
    }

    @PostMapping("/{patrolId}/incidents")
    public void addIncident(@PathVariable Long patrolId,
                            @Valid @RequestBody IncidentRequest request) {
        incidentService.recordIncident(patrolId, request.getDescription());
    }

    @PostMapping("/{patrolId}/end")
    public void endPatrol(@PathVariable Long patrolId) {
        patrolService.endPatrol(patrolId);
    }

    @GetMapping("/{patrolId}")
    public PatrolResponse getPatrol(@PathVariable Long patrolId) {
        return patrolService.getPatrol(patrolId);
    }

    @GetMapping("/{patrolId}/report")
    public ReportResponse getReport(@PathVariable Long patrolId) {
        return patrolService.getReport(patrolId);
    }
}