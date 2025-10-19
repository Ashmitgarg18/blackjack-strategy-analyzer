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
public class SimulationSummaryDTO {

    private Long id;

    private String strategyName;

    private int rounds;

    private int decks;

    private double penetration;

    private double roiPercent;

    private LocalDateTime createdAt;

}
