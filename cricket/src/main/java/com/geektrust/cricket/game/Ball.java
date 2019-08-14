package com.geektrust.cricket.game;


import com.geektrust.cricket.utils.BallOutcomesEnum;
import com.geektrust.cricket.utils.Constants;

public class Ball {

    private final String batsmanName;
    private final int ballNumber;
    private final BallOutcomesEnum ballOutcome;

    public Ball(String batsmanName, int ballNumber, BallOutcomesEnum ballOutcome) {
        this.batsmanName = batsmanName;
        this.ballNumber = ballNumber;
        this.ballOutcome = ballOutcome;
    }

    public boolean shouldBatsmanChangeStrike() {
        return ballOutcome != BallOutcomesEnum.OUT && ballOutcome.runsScored() % 2 != 0;
    }

    public boolean isLastBallOfOver() {
        return ballNumber == Constants.ballsInAnOver;
    }

    public String toString() {
        return ballNumber + " " + batsmanName + ballOutcome.describe() + "\n";
    }
}
