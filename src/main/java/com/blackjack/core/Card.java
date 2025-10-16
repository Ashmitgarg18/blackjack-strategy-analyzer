package com.blackjack.core;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank){
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit(){
        return suit;
    }

    public Rank getRank(){
        return rank;
    }

    public boolean isACE(){
        return rank == Rank.ACE;
    }

    @Override
    public String toString(){
        return rank.name() + " of " + suit.name();
    }

}
