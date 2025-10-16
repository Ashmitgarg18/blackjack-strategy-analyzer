package com.blackjack.core;

public interface Strategy {
    Action decide(
            Hand playerHand,
            Card dealerUpCard,
            GameState gameState,
            boolean canSplit,
            boolean canDouble,
            boolean canSurrender
    );

    int betSize(GameState gameState);

    String name();
}
