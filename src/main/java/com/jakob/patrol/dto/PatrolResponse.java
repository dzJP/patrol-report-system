package com.jakob.patrol.dto;

import com.jakob.patrol.model.Incident;
import com.jakob.patrol.model.PatrolRound;
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

    private List<PatrolRound> rounds;
    private List<Incident> incidents;

}