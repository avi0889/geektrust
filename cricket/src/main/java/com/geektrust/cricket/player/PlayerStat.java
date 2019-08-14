package com.geektrust.cricket.player;


import com.geektrust.cricket.utils.BallOutcomesEnum;

public class PlayerStat {
    private int runsScored;
    private int ballsPlayed;
    private boolean isBatting;

    public PlayerStat(boolean isBatting) {
        this.isBatting = isBatting;
    }

    public void updateStat(BallOutcomesEnum ballOutcome) {
        if(ballOutcome == BallOutcomesEnum.OUT) {
            isBatting = false;
        } else {
            runsScored += ballOutcome.runsScored();
        }
        this.ballsPlayed++;
    }

    public void setIsBatting() {
        isBatting = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" - ");
        sb.append(runsScored);
        if(isBatting)
            sb.append("*");
        sb.append(" (");
        sb.append(ballsPlayed);
        if(ballsPlayed == 1)
            sb.append(" ball)");
        else
            sb.append(" balls)");
        return sb.toString();
    }
}
