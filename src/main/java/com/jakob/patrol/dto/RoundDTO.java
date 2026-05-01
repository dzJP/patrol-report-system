package com.jakob.patrol.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoundDTO {

    private String location;
    private LocalDateTime time;

}