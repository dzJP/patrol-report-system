package com.jakob.patrol.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Patrol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private PatrolStatus status;

    @OneToMany(mappedBy = "patrol", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PatrolRound> rounds;
}