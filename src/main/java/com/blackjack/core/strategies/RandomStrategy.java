package com.blackjack.core.strategies;

import com.blackjack.core.*;

import java.util.random.RandomGenerator;

public class RandomStrategy implements Strategy {
    private final RandomGenerator rng;
    private final int flatBet;

    public RandomStrategy(RandomGenerator rng, int flatBet){
        this.rng = rng;
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
        Action[] actions = new Action[]{Action.HIT, Action.STAND};

        if(canDouble) actions = new Action[]{Action.HIT, Action.STAND, Action.DOUBLE};

        int index = (int) (Math.abs(rng.nextLong()) % actions.length);
        return actions[index];
    }

    @Override
    public int betSize(GameState gameState){
        return flatBet;
    }

    @Override
    public String name(){
        return "Random strategy with flat bet";
    }
}
