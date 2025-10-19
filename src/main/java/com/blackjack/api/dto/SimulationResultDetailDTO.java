package com.blackjack.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationResultDetailDTO {

    private Long id;

    private String strategyName;

    private int rounds;

    private int decks;

    private double penetration;

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
