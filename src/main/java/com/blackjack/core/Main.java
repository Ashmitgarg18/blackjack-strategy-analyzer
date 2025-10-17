package com.blackjack.core;

import com.blackjack.core.strategies.FlatBasicStrategy;
import com.blackjack.core.strategies.HiLoCounter;
import com.blackjack.core.strategies.RandomStrategy;

import java.util.Comparator;
import java.util.Map;
import java.util.random.RandomGenerator;

public class Main {

    public static void main(String[] args) {

        SimulationConfig cfg = SimulationConfig.defaultConfig();

        RandomStrategy random = new RandomStrategy(RandomGenerator.getDefault(), cfg.getMinBet());
        FlatBasicStrategy basic = new FlatBasicStrategy(cfg.getMinBet());
        HiLoCounter hilo = new HiLoCounter(cfg.getMinBet(), cfg.getMinBet() * 8);

        runAndPrint(cfg, random, 4);
        runAndPrint(cfg, basic, 4);
        runAndPrint(cfg, hilo, 4);
    }

    private static void runAndPrint(SimulationConfig cfg, Strategy strategy, int threads) {
        long seed = System.currentTimeMillis();

        long start = System.currentTimeMillis();
        Stats stats = Simulator.run(cfg, strategy, threads, seed);
        long duration = System.currentTimeMillis() - start;

        long hands = stats.getHands();
        long wins = stats.getWins();
        long losses = stats.getLosses();
        long pushes = stats.getPushes();
        long blackjacks = stats.getBlackjacks();
        long playerBusts = stats.getPlayerBusts();
        long dealerBusts = stats.getDealerBusts();
        long netUnits = stats.getNetUnits();
        long totalBet = stats.getTotalBet();

        System.out.println("\n==============================");
        System.out.println("Strategy: " + strategy.name());
        System.out.println("Hands: " + hands);
        System.out.printf("W: %d (%.2f%%), L: %d (%.2f%%), P: %d (%.2f%%)%n",
                wins, percent(wins, hands), losses, percent(losses, hands), pushes, percent(pushes, hands));
        System.out.println("Blackjacks: " + blackjacks);
        System.out.println("Player Busts: " + playerBusts);
        System.out.println("Dealer Busts: " + dealerBusts);
        System.out.println("Net Units: " + netUnits);
        System.out.println("Total Bet: " + totalBet);
        System.out.printf("EV per hand: %.6f%n", (double) netUnits / hands);
        System.out.printf("Average Bet: %.2f%n", (double) totalBet / hands);
        System.out.printf("Return on Investment: %.4f%%%n", (double) netUnits / totalBet * 100);


        if (strategy.name().contains("Counter")) {
            System.out.println("\nTrue Count Distribution:");
            stats.getTrueCountFrequency().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        int tc = entry.getKey();
                        long count = entry.getValue().sum();
                        double pct = (double) count / hands * 100;
                        System.out.printf("  TC %2d: %7d hands (%.2f%%)%n", tc, count, pct);
                    });
        }

        System.out.println("Time: " + duration + " ms");
    }

    private static double percent(long part, long total) {
        return total == 0 ? 0.0 : (part * 100.0 / total);
    }
}
