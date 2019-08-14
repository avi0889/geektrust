package com.geektrust.cricket.game;


import com.geektrust.cricket.utils.BallOutcomesEnum;
import com.geektrust.cricket.utils.Constants;

public class Over {

    private int ballNumber;
    private final int overNumber;
    private Ball[] balls;

    public Over(int overNumber) {
        ballNumber = -1;
        this.overNumber = overNumber;
        balls = new Ball[Constants.ballsInAnOver];
    }

    public void updateBall(BallOutcomesEnum ballOutcome, String batsmanName) {
        ballNumber++;
        balls[ballNumber] = new Ball(batsmanName, ballNumber + 1, ballOutcome);
    }

    public boolean isStrikeChange() {
        if(ballNumber < 0)
            return false;
        if(!isComplete())
            return balls[ballNumber].shouldBatsmanChangeStrike();
        return !balls[ballNumber].shouldBatsmanChangeStrike();
    }

    public boolean isComplete() {
        if(ballNumber < 0)
            return false;
        if(balls[ballNumber] == null)
            return false;
        return balls[ballNumber].isLastBallOfOver();
    }

    public int ballRemaining() {
        return Constants.ballsInAnOver - ballNumber - 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= ballNumber; i++) {
            sb.append(overNumber);
            sb.append(".");
            sb.append(balls[i].toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
