package com.blackjack.api.dto;

import com.blackjack.core.GameRules;

public record SimulationResponse(
        String strategyName,
        int rounds,
        int decks,
        double penetration,
        GameRules rules,

        long hands,
        long wins,
        long losses,
        long pushes,
        long blackjacks,
        long playerBusts,
        long dealerBusts,

        long netUnits,
        long totalBet,

        double evPerHand,
        double averageBet,
        double roiPercent
) { }
