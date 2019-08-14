package com.geektrust.cricket.game;


import com.geektrust.cricket.player.Player;
import com.geektrust.cricket.utils.BallOutcomesEnum;
import com.geektrust.cricket.utils.Constants;

import java.util.Iterator;

public class ScoreBoard {

    private int currentScore;
    private final int totalRunsRequired;
    private int wicketsFallen;
    private Over[] overs;
    private int overNumber;
    private Player striker;
    private Player nonStriker;
    private Iterator<Player> batsmenIterator;

    public ScoreBoard(int totalRunsRequired, int currentScore, Iterator<Player> batsmenIterator) {
        this.totalRunsRequired = totalRunsRequired;
        this.currentScore = currentScore;
        this.batsmenIterator = batsmenIterator;
        this.striker = this.batsmenIterator.next();
        this.nonStriker = this.batsmenIterator.next();
        this.overs = new Over[Constants.numberOfOvers];
        for(int i = 0; i < Constants.numberOfOvers; i++)
            this.overs[i] = new Over(i);
    }

    public void updateStat(BallOutcomesEnum ballOutcome) {
        //check if over completed on last ball and increment accordingly
        if(overs[overNumber].isComplete())
            overNumber++;

        //set this ball in the over
        overs[overNumber].updateBall(ballOutcome, striker.getName());

        //increment the score
        currentScore += ballOutcome.runsScored();

        //increment wicket column if this ball resulted in a wicket
        if(ballOutcome.isOut())
            wicketsFallen++;

        setStrike(ballOutcome);
    }

    private void setStrike(BallOutcomesEnum ballOutcome) {
        //bring new batsman in if this ball resulted in a wicket
        if (ballOutcome == BallOutcomesEnum.OUT) {
            if(batsmenIterator.hasNext()) {
                striker = batsmenIterator.next();
                striker.setIsBatting();
            }
        }
        //should strike be rotated
        if(isStrikeChange()) {
            Player temp = striker;
            striker = nonStriker;
            nonStriker = temp;
        }
    }

    public Player getStriker() {
        return striker;
    }

    public boolean isStrikeChange() {
        return overs[overNumber].isStrikeChange();
    }

    public boolean isOver() {
        return isWon() || isTied() || isLost();
    }

    public boolean isTied() {
        return currentScore == totalRunsRequired && (isLastOverComplete() || wicketsFallen >= Constants.wicketsInHand);
    }

    public boolean isWon() {
        return currentScore > totalRunsRequired;
    }

    public boolean isLost() {
        return (currentScore < totalRunsRequired) && (isLastOverComplete() || wicketsFallen >= Constants.wicketsInHand);
    }

    private boolean isLastOverComplete() {
        if(overs[Constants.numberOfOvers - 1] == null)
            return false;
        return overs[Constants.numberOfOvers - 1].isComplete();
    }

    private int overRemaining() {
        return Constants.numberOfOvers - overNumber - 1;
    }

    private int ballsRemainingInMatch() {
        return (Constants.ballsInAnOver * overRemaining()) + overs[overNumber].ballRemaining();
    }

    private int runsRemainingInMatch() {
        return totalRunsRequired - currentScore;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= overNumber; i++) {
            sb.append(overs[i].toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    public String printSummary() {
        if(isWon()) {
            return printWinSummary();
        } else if(isLost()) {
            return printLoseSummary();
        } else {
            return printTieSummary();
        }
    }

    private String printTieSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Math tied with ");
        sb.append(Constants.wicketsInHand - wicketsFallen);
        if(Constants.wicketsInHand - wicketsFallen == 1)
            sb.append(" wicket and ");
        else
            sb.append(" wickets and ");
        sb.append(ballsRemainingInMatch());
        if(ballsRemainingInMatch() == 1)
            sb.append(" ball remaining");
        else
            sb.append(" balls remaining");
        return sb.toString();
    }

    private String printLoseSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.bowlingTeamName);
        sb.append(" won with ");
        sb.append(runsRemainingInMatch());
        if(runsRemainingInMatch() == 1)
            sb.append(" run");
        else
            sb.append(" runs");
        return sb.toString();
    }

    private String printWinSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.battingTeamName);
        sb.append(" won by ");
        sb.append(Constants.wicketsInHand - wicketsFallen);
        if(Constants.wicketsInHand - wicketsFallen == 1)
            sb.append(" wicket and ");
        else
            sb.append(" wickets and ");
        sb.append(ballsRemainingInMatch());
        if(ballsRemainingInMatch() == 1)
            sb.append(" ball remaining");
        else
            sb.append(" balls remaining");
        return sb.toString();
    }
}
