package com.blackjack.core.strategies;

import com.blackjack.core.*;

public class HiLoCounter implements Strategy {
    private final int minBet;
    private final int maxBet;
    private final FlatBasicStrategy basicStrategy;

    public HiLoCounter(int minBet, int maxBet){
        this.maxBet = maxBet;
        this.minBet = minBet;
        this.basicStrategy = new FlatBasicStrategy(minBet);
    }

    @Override
    public Action decide(
            Hand playerHand,
            Card dealerUpCard,
            GameState gameState,
            boolean canDouble,
            boolean canSplit,
            boolean canSurrender
    ){
        return basicStrategy.decide(playerHand, dealerUpCard, gameState, canDouble, canSplit, canSurrender);
    }

    @Override
    public int betSize(GameState gameState){
        int trueCount = gameState.getTrueCount();

        if(trueCount <= 0) return minBet;
        if(trueCount >= 4) return maxBet;

        int spread = maxBet - minBet;
        return minBet + (spread * trueCount) / 4;

    }

    @Override
    public String name() {
        return "Hi-Lo Counter (Bet Ramp)";
    }

}
