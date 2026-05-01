package com.jakob.patrol.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IncidentDTO {

    private String description;
    private LocalDateTime time;

}