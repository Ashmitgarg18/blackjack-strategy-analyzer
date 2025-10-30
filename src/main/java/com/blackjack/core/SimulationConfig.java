package com.blackjack.core;

public class SimulationConfig {

    private final int rounds;
    private final int numberOfDecks;
    private final double penetration;
    private final GameRules rules;
    private final int startingBankroll;
    private final int minBet;

    public SimulationConfig(int rounds,
                            int numberOfDecks,
                            double penetration,
                            GameRules rules,
                            int startingBankroll,
                            int minBet) {
        this.rounds = rounds;
        this.numberOfDecks = numberOfDecks;
        this.penetration = penetration;
        this.rules = rules;
        this.startingBankroll = startingBankroll;
        this.minBet = minBet;
    }

    public int getRounds() {
        return rounds;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public double getPenetration() {
        return penetration;
    }

    public GameRules getRules() {
        return rules;
    }

    public int getStartingBankroll() {
        return startingBankroll;
    }

    public int getMinBet() {
        return minBet;
    }

    public static SimulationConfig defaultConfig() {
        return new SimulationConfig(
                1_000_000,
                8,
                0.75,
                GameRules.standard(),
                100_000,
                10
        );
    }
}
