package com.blackjack.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.random.RandomGenerator;

public class Shoe {
    private final List<Card> cards;
    private final int cutIndex;
    private int currentIndex;
    private final RandomGenerator rng;
    private boolean justShuffled;
    private final int totalCards;

    public Shoe(int numOfDecks, double penetration, RandomGenerator rng) {
        if (numOfDecks < 1) throw new IllegalArgumentException("number of decks should be >= 1");
        if (penetration <= 0 || penetration >= 1) throw new IllegalArgumentException("penetration must be between 0 and 1 (exclusive)");

        this.rng = rng;
        this.cards = new ArrayList<>(numOfDecks * 52);

        for (int d = 0; d < numOfDecks; d++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(suit, rank));
                }
            }
        }

        this.totalCards = cards.size();
        shuffle();
        this.cutIndex = (int) Math.floor(cards.size() * penetration);
    }

    public void shuffle() {
        Collections.shuffle(cards, new java.util.Random(rng.nextLong()));
        currentIndex = 0;
        justShuffled = true;
    }

    public Card deal() {
        if (currentIndex >= cutIndex) {
            shuffle();
        }
        // Do not reset justShuffled here. Let the caller detect it and clear via wasJustShuffled().
        return cards.get(currentIndex++);
    }

    public boolean wasJustShuffled() {
        boolean result = justShuffled;
        justShuffled = false;
        return result;
    }

    public int getCardsUntilShuffle() {
        return cutIndex - currentIndex;
    }

    public int getCardsDealt() {
        return currentIndex;
    }

    public int getTotalCards() {
        return totalCards;
    }
}
