package com.jakob.patrol.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportResponse {

    private Long patrolId;
    private int totalRounds;
    private int totalIncidents;

    private List<String> incidentSummaries;

}