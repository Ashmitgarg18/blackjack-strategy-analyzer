package com.blackjack.core;

public record GameRules(
        boolean dealerHitsSoft17,
        double blackjackPayout,
        boolean doubleAfterSplit,
        boolean surrenderAllowed,
        int maxSplits
) {
    public static GameRules standard(){
        return new GameRules(
                true,
                1.5,
                true,
                false,
                3
        );
    }
}
