package com.blackjack.core.strategies;

import com.blackjack.core.*;

public class FlatBasicStrategy implements Strategy{
    private final int flatBet;

    public FlatBasicStrategy(int flatBet){
        this.flatBet = flatBet;
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
        int playerValue = playerHand.bestValue();
        int dealerValue = dealerUpCard.getRank().getValue();

        if (canSplit && isPair(playerHand)) {
            if (playerHand.getCards().get(0).getRank() == Rank.ACE ||
                    playerHand.getCards().get(0).getRank() == Rank.EIGHT) {
                return Action.SPLIT;
            }
        }

        if (canDouble) {
            if (!playerHand.isSoft()) {
                if (playerValue == 11) return Action.DOUBLE;
                if (playerValue == 10 && dealerValue < 10) return Action.DOUBLE;
                if (playerValue == 9 && dealerValue >= 3 && dealerValue <= 6) return Action.DOUBLE;
            } else {
                if (playerValue == 18 && dealerValue >= 3 && dealerValue <= 6) return Action.DOUBLE;
                if (playerValue == 17 && dealerValue >= 3 && dealerValue <= 6) return Action.DOUBLE;
                if (playerValue == 16 && dealerValue >= 4 && dealerValue <= 6) return Action.DOUBLE;
                if (playerValue == 15 && dealerValue >= 4 && dealerValue <= 6) return Action.DOUBLE;
                if (playerValue == 14 && dealerValue >= 5 && dealerValue <= 6) return Action.DOUBLE;
                if (playerValue == 13 && dealerValue >= 5 && dealerValue <= 6) return Action.DOUBLE;
            }
        }

        if (!playerHand.isSoft()) {
            if (playerValue >= 17) return Action.STAND;
            if (playerValue >= 13 && dealerValue <= 6) return Action.STAND;
            if (playerValue == 12 && dealerValue >= 4 && dealerValue <= 6) return Action.STAND;
            return Action.HIT;
        } else {
            if (playerValue >= 19) return Action.STAND;
            if (playerValue == 18 && dealerValue <= 8) return Action.STAND;
            return Action.HIT;
        }

    }

    private boolean isPair(Hand hand) {
        return hand.getCards().size() == 2 &&
                hand.getCards().get(0).getRank() == hand.getCards().get(1).getRank();
    }

    @Override
    public int betSize(GameState gameState) {
        return flatBet;
    }

    @Override
    public String name() {
        return "Basic Strategy (Flat Bet)";
    }
}
