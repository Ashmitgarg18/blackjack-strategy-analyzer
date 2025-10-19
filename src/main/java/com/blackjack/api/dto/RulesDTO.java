package com.blackjack.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record RulesDTO(
        boolean dealerHitsSoft17,
        @Min(1) @Max(3) double blackjackPayout, // 1.5 for 3:2, 1.2 for 6:5
        boolean doubleAfterSplit,
        boolean surrenderAllowed,
        @Positive int maxSplits
) {
    public static RulesDTO standard() {
        return new RulesDTO(true, 1.5, true, false, 3);
    }
}
