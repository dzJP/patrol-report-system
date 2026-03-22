package com.jakob.patrol.service;

import com.jakob.patrol.model.PatrolRound;
import com.jakob.patrol.repository.PatrolRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatrolService {

    private final PatrolRepository patrolRepository;


    public PatrolService(PatrolRepository patrolRepository) {
        this.patrolRepository = patrolRepository;
    }

    public void recordRound(String location) {
        PatrolRound round = new PatrolRound();
        round.setLocation(location);
        round.setTime(LocalDateTime.now());
        patrolRepository.save(round);
    }

    public List<PatrolRound> getAllRounds() {
        return patrolRepository.findAll();
    }
}
