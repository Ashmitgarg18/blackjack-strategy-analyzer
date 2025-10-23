package com.blackjack.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulation_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String strategyName;

    private int rounds;

    private int decks;

    private double penetration;

    @Column
    private String rules;

    private long hands;

    private long wins;

    private long losses;

    private long pushes;

    private long blackjacks;

    private long playerBusts;

    private long dealerBusts;

    private long netUnits;

    private long totalBet;

    private double evPerHand;

    private double averageBet;

    private double roiPercent;

    private LocalDateTime createdAt;

}
