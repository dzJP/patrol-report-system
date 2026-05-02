package com.jakob.patrol.repository;

import com.jakob.patrol.model.PatrolRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatrolRoundRepository extends JpaRepository<PatrolRound, Long> {

    List<PatrolRound> findByPatrolId(Long patrolId);
}
