package com.geektrust.cricket.player;

import com.geektrust.cricket.outcome.Outcome;
import com.geektrust.cricket.utils.BallOutcomesEnum;

/**
 * actions of striker on this ball should not affect actions of non striker on next ball,
 * hence each player will carry his own Randomizer
 */
public class Player {

    private String name;
    private PlayerStat stat;
    private Outcome randomOutcome;

    public Player(String name, Outcome randomOutcome, boolean isBatting) {
        this.name = name;
        this.randomOutcome = randomOutcome;
        stat = new PlayerStat(isBatting);
    }

    public BallOutcomesEnum play() {
        int outcomeIndex = (Integer) randomOutcome.getOutcome();
        BallOutcomesEnum ballOutcome = BallOutcomesEnum.getBallOutcome(outcomeIndex);
        stat.updateStat(ballOutcome);
        return ballOutcome;
    }

    public String getName() {
        return name;
    }

    public void setIsBatting() {
        stat.setIsBatting();
    }

    public String toString() {
        return name + stat.toString();
    }
}
