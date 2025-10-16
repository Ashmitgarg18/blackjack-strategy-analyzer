package com.blackjack.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card){
        cards.add(card);
    }

    public List<Card> getCards(){
        return Collections.unmodifiableList(cards);
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


}
