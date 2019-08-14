package com.geektrust.goldencrown.election;

import com.geektrust.kingdom.Kingdom;
import com.geektrust.utils.Constants;
import com.geektrust.utils.Election;

public class GoldenCrownElection implements Election {

    private Kingdom contestant;
    private Kingdom winner;

    @Override
    public void addContestant(Kingdom contestant) {
        this.contestant = contestant;
    }

    @Override
    public void elect() {
        if(contestant.numberOfAllies() >= Constants.goldenCrownNumberOfAlliesRequired)
            winner = contestant;
        else
            winner = null;
    }

    @Override
    public Kingdom winner() {
        return winner;
    }
}
