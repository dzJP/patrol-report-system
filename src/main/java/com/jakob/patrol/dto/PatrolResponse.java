package com.jakob.patrol.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PatrolResponse {

    private Long id;
    private String username;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private List<RoundDTO> rounds;
    private List<IncidentDTO> incidents;

}