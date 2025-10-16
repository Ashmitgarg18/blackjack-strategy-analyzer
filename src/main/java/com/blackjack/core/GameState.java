package com.blackjack.core;

public class GameState {
    private final int trueCount;
    private final GameRules gameRules;

    public GameState(int trueCount, GameRules gameRules){
        this.trueCount = trueCount;
        this.gameRules = gameRules;
    }

    public int getTrueCount(){
        return trueCount;
    }

    public GameRules getGameRules(){
        return gameRules;
    }
}
