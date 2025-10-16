package com.blackjack.core;

import java.util.concurrent.atomic.LongAdder;

public class Stats {

    private final LongAdder hands = new LongAdder();
    private final LongAdder wins = new LongAdder();
    private final LongAdder losses = new LongAdder();
    private final LongAdder pushes = new LongAdder();
    private final LongAdder blackjacks = new LongAdder();
    private final LongAdder playerBusts = new LongAdder();
    private final LongAdder dealerBusts = new LongAdder();
    private final LongAdder netUnits = new LongAdder();

    public void incrementHands() { hands.increment(); }
    public void incrementWins() { wins.increment(); }
    public void incrementLosses() { losses.increment(); }
    public void incrementPushes() { pushes.increment(); }
    public void incrementBlackjacks() { blackjacks.increment(); }
    public void incrementPlayerBusts() { playerBusts.increment(); }
    public void incrementDealerBusts() { dealerBusts.increment(); }
    public void addNetUnits(long units) { netUnits.add(units); }

    public long getHands() { return hands.sum(); }
    public long getWins() { return wins.sum(); }
    public long getLosses() { return losses.sum(); }
    public long getPushes() { return pushes.sum(); }
    public long getBlackjacks() { return blackjacks.sum(); }
    public long getPlayerBusts() { return playerBusts.sum(); }
    public long getDealerBusts() { return dealerBusts.sum(); }
    public long getNetUnits() { return netUnits.sum(); }


    public void merge(Stats other) {
        this.hands.add(other.getHands());
        this.wins.add(other.getWins());
        this.losses.add(other.getLosses());
        this.pushes.add(other.getPushes());
        this.blackjacks.add(other.getBlackjacks());
        this.playerBusts.add(other.getPlayerBusts());
        this.dealerBusts.add(other.getDealerBusts());
        this.netUnits.add(other.getNetUnits());
    }
}
