package com.blackjack.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.Map;

public record SimulationRequest(

        @NotBlank String strategy,

        Map<String, Object> strategyParams,

        @Min(1) @Max(50_000_000) int rounds,
        @Min(1) @Max(8) int decks,
        @DecimalMin(value = "0.5") @DecimalMax(value = "0.99") double penetration,

        @Valid RulesDTO rules,

        @Min(1) int tableMinBet,
        @Min(0) int startingBankroll,

        @Min(1) @Max(64) int threads,

        long seed
) {
    public static SimulationRequest quickBasic() {
        return new SimulationRequest(
                "BASIC",
                Map.of(),
                1_000_000,
                6,
                0.75,
                RulesDTO.standard(),
                10,
                100_000,
                4,
                42L
        );
    }
}
