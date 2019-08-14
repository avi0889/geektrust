package com.geektrust.cricket.game;


import com.geektrust.cricket.player.Player;
import com.geektrust.cricket.utils.BallOutcomesEnum;

import java.util.List;

public class Game {

    private List<Player> players;
    private ScoreBoard scoreBoard;

    public Game(List<Player> players, ScoreBoard scoreBoard) {
        this.players = players;
        this.scoreBoard = scoreBoard;
    }

    public void begin() {
        //run until the game is not over
        while(!scoreBoard.isOver()) {
            //ask the striker batsman to play
            BallOutcomesEnum ballOutcome = scoreBoard.getStriker().play();
            //update the stats according to ball's outcome
            scoreBoard.updateStat(ballOutcome);
        }
    }

    public String toString() {
        return scoreBoard.toString();
    }

    public String printSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(scoreBoard.printSummary());
        sb.append("\n\n");
        for(Player player : players) {
            sb.append(player.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
