package com.blackjack.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();
    private double bet = 1.0;
    private boolean isSplitAces = false;
    private boolean isCompleted = false;

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(cards);
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public boolean isSplitAces() {
        return isSplitAces;
    }

    public void setSplitAces(boolean splitAces) {
        this.isSplitAces = splitAces;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public int handValue(){
        int sum = 0;
        for(Card c : cards){
            sum += c.getRank().getValue();
        }
        return sum;
    }

    public int bestValue(){
        int sum = 0;
        int aces = 0;

        for(Card c : cards){
            sum += c.getRank().getValue();
            if(c.isACE()){
                aces++;
            }
        }

        while(sum > 21 && aces > 0){
            sum -= 10;
            aces--;
        }

        return sum;
    }

    public boolean isSoft(){
        int sum = 0;
        int aces = 0;

        for(Card c : cards){
            sum += c.getRank().getValue();
            if(c.isACE()){
                aces++;
            }
        }

        return aces > 0 && sum <= 21;
    }

    public boolean isBlackjack(){
        return cards.size() == 2 && bestValue() == 21;
    }

    public boolean isBust(){
        return bestValue() > 21;
    }

    public boolean canSplit() {
        if (cards.size() != 2) return false;
        return cards.get(0).getRank() == cards.get(1).getRank();
    }

    public Hand split() {
        if (!canSplit()) throw new IllegalStateException("Cannot split this hand");
        Card secondCard = cards.remove(1);
        Hand newHand = new Hand();
        newHand.addCard(secondCard);
        newHand.setBet(this.bet);
        if (cards.get(0).isACE()) {
            this.isSplitAces = true;
            newHand.setSplitAces(true);
        }
        return newHand;
    }

    public String toString() {
        return cards.toString() + " (" + bestValue() + ")";
    }

}
