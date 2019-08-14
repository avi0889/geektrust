package com.geektrust.cricket.utils;


public enum BallOutcomesEnum {
    DOT_BALL(0, 0, true, " scores no run"),
    ONE_RUN(1, 1, true, " scores 1 run"),
    TWO_RUNS(2, 2, true, " scores 2 runs"),
    THREE_RUNS(3, 3, true, " scores 3 runs"),
    FOUR_RUNS(4, 4, true, " scores 4 runs"),
    FIVE_RUNS(5, 5, true, " scores 5 runs"),
    SIX_RUNS(6, 6, true, " scores 6 runs"),
    OUT(7, 0, true, " gets out");

    private int index;
    private int score;
    private boolean isLegal;
    private String description;

    BallOutcomesEnum(int index, int score, boolean isLegal, String description) {
        this.index = index;
        this.score = score;
        this.isLegal = isLegal;
        this.description = description;
    }

    public int runsScored() {
        return score;
    }

    public boolean isLegal() {
        return isLegal;
    }

    public String describe() {
        return description;
    }

    public boolean isOut() {
        return index == 7;
    }

    public static BallOutcomesEnum getBallOutcome(int outcomeInd) {
        for(BallOutcomesEnum ballOutcomesEnum : values()) {
            if(ballOutcomesEnum.index == outcomeInd) {
                return ballOutcomesEnum;
            }
        }
        return BallOutcomesEnum.DOT_BALL;
    }
}
