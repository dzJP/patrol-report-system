package com.jakob.patrol.repository;

import com.jakob.patrol.model.Patrol;
import com.jakob.patrol.model.PatrolRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrolRepository extends JpaRepository<Patrol, Long> {
}
