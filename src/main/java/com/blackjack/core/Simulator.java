package com.blackjack.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.random.RandomGenerator;

public class Simulator {

    private Simulator() {}

    public static Stats run(SimulationConfig config, Strategy strategy, int threads, long seed) {
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        List<Future<Stats>> futures = new ArrayList<>();

        int per = config.getRounds() / threads;
        int rem = config.getRounds() % threads;

        for (int t = 0; t < threads; t++) {
            int roundsForThread = per + (t < rem ? 1 : 0);
            final long threadSeed = seed + t * 97_373L;
            futures.add(pool.submit(() -> runChunk(config, strategy, roundsForThread, threadSeed)));
        }

        pool.shutdown();

        Stats total = new Stats();
        for (Future<Stats> future : futures) {
            try {
                total.merge(future.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        return total;
    }

    private static Stats runChunk(SimulationConfig cfg, Strategy strategy, int rounds, long seed) {

        RandomGenerator rng = java.util.random.RandomGeneratorFactory
                .of("L64X128MixRandom")
                .create(seed);

        Shoe shoe = new Shoe(cfg.getNumberOfDecks(), cfg.getPenetration(), rng);
        GameRules rules = cfg.getRules();
        Stats stats = new Stats();

        int runningCount = 0;

        for (int i = 0; i < rounds; i++) {
            Hand player = new Hand();
            Hand dealer = new Hand();

            if (shoe.wasJustShuffled()) {
                runningCount = 0;
            }

            Card p1 = shoe.deal();

            runningCount += hiloDelta(p1);
            player.addCard(p1);

            Card d1 = shoe.deal();
            runningCount += hiloDelta(d1);
            dealer.addCard(d1);

            Card p2 = shoe.deal();
            runningCount += hiloDelta(p2);
            player.addCard(p2);

            Card d2 = shoe.deal();
            runningCount += hiloDelta(d2);
            dealer.addCard(d2);

            stats.incrementHands();

            int cardsUntilShuffle = shoe.getCardsUntilShuffle();
            int decksRemaining = Math.max(1, (cardsUntilShuffle + 51) / 52);
            int trueCount = runningCount / decksRemaining;

            stats.recordTrueCount(trueCount);

            GameState state = new GameState(trueCount, rules);

            int betUnits = strategy.betSize(state);
            if (betUnits < cfg.getMinBet()) {
                betUnits = cfg.getMinBet();
            }

            stats.addTotalBet(betUnits);

            boolean playerBlackjack = player.isBlackjack();
            boolean dealerBlackjack = dealer.isBlackjack();

            if (playerBlackjack || dealerBlackjack) {
                if (playerBlackjack && !dealerBlackjack) {
                    stats.incrementBlackjacks();
                    stats.incrementWins();
                    stats.addNetUnits((long) Math.round(betUnits * rules.blackjackPayout()));
                } else if (!playerBlackjack && dealerBlackjack) {
                    stats.incrementLosses();
                    stats.addNetUnits(-betUnits);
                } else {
                    stats.incrementPushes();
                }
                continue;
            }

            boolean canDouble = true;
            boolean finished = false;

            while (!finished) {
                Action action = strategy.decide(player, dealer.getCards().get(0), state, canDouble, false, false);

                switch (action) {
                    case STAND -> {
                        finished = true;
                    }

                    case HIT -> {
                        Card c = shoe.deal();
                        runningCount += hiloDelta(c);
                        player.addCard(c);
                        canDouble = false;

                        if (player.isBust()) {
                            stats.incrementPlayerBusts();
                            stats.incrementLosses();
                            stats.addNetUnits(-betUnits);
                            finished = true;
                        }
                    }

                    case DOUBLE -> {
                        if (canDouble) {
                            betUnits *= 2;
                            Card c = shoe.deal();
                            runningCount += hiloDelta(c);
                            player.addCard(c);
                            canDouble = false;
                            finished = true;

                            if (player.isBust()) {
                                stats.incrementPlayerBusts();
                                stats.incrementLosses();
                                stats.addNetUnits(-betUnits);
                            }
                        } else {
                            // treat as HIT
                            Card c = shoe.deal();
                            runningCount += hiloDelta(c);
                            player.addCard(c);
                            canDouble = false;

                            if (player.isBust()) {
                                stats.incrementPlayerBusts();
                                stats.incrementLosses();
                                stats.addNetUnits(-betUnits);
                                finished = true;
                            }
                        }
                    }

                    default -> {
                        finished = true;
                    }
                }
            }

            if (player.isBust()) {
                continue;
            }

            boolean dealerResolved = false;

            while (true) {
                int dealerValue = dealer.bestValue();
                boolean dealerSoft = dealer.isSoft();

                if (dealerValue > 21) {
                    stats.incrementDealerBusts();
                    stats.incrementWins();
                    stats.addNetUnits(betUnits);
                    dealerResolved = true;
                    break;
                }

                if (dealerValue > 17
                        || (dealerValue == 17 && (!rules.dealerHitsSoft17() || !dealerSoft))) {
                    break;
                }

                Card c = shoe.deal();
                runningCount += hiloDelta(c);
                dealer.addCard(c);
            }

            if (dealerResolved) {
                continue;
            }

            int playerValue = player.bestValue();
            int dealerValue = dealer.bestValue();

            if (playerValue > dealerValue) {
                stats.incrementWins();
                stats.addNetUnits(betUnits);
            } else if (dealerValue > playerValue) {
                stats.incrementLosses();
                stats.addNetUnits(-betUnits);
            } else {
                stats.incrementPushes();
            }
        }

        return stats;
    }

    private static int hiloDelta(Card c) {
        return switch (c.getRank()) {
            case TWO, THREE, FOUR, FIVE, SIX -> 1;
            case SEVEN, EIGHT, NINE -> 0;
            default -> -1;
        };
    }
}
