package com.blackjack.persistence.repo;

import com.blackjack.persistence.entity.SimulationResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimulationResultRepository extends JpaRepository<SimulationResultEntity, Long> {

    List<SimulationResultEntity> findByStrategyName(String strategyName);

}
