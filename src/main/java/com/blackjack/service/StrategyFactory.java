package com.blackjack.service;

import com.blackjack.core.Strategy;
import com.blackjack.core.strategies.FlatBasicStrategy;
import com.blackjack.core.strategies.HiLoCounter;
import com.blackjack.core.strategies.RandomStrategy;

import java.util.Map;
import java.util.random.RandomGenerator;

public class StrategyFactory {

    public enum StrategyType{
        RANDOM,
        BASIC,
        HILO
    }

    public static Strategy create(StrategyType strategyType, int tableMinBet, Map<String, Object> params, long seed){
        switch (strategyType){
            case RANDOM -> {
                RandomGenerator rng = java.util.random.RandomGeneratorFactory
                        .of("L64X128MixRandom")
                        .create(seed);
                return new RandomStrategy(rng, tableMinBet);
            }
            case BASIC -> {
                return new FlatBasicStrategy(tableMinBet);
            }
            case HILO -> {
                int max = params != null && params.get("maxBet") instanceof Number
                        ? ((Number) params.get("maxBet")).intValue()
                        : tableMinBet * 8;
                max = Math.max(tableMinBet, max);
                return new HiLoCounter(tableMinBet, max);
            }
            default -> throw new IllegalArgumentException("Unknown strategy type" + strategyType);
        }

    }

}
