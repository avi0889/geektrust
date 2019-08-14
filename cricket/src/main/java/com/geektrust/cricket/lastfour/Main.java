package com.geektrust.cricket.lastfour;


import com.geektrust.cricket.game.Game;
import com.geektrust.cricket.random.RandomizerImpl;
import com.geektrust.cricket.game.ScoreBoard;
import com.geektrust.cricket.outcome.RandomOutcome;
import com.geektrust.cricket.player.Player;
import com.geektrust.cricket.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //prepare the list of players
        List<Player> playerList = new ArrayList<>();
        Player kohli = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        playerList.add(kohli);
        Player dhoni = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        playerList.add(dhoni);
        Player bumrah = new Player(Constants.bumrahName, new RandomOutcome(
                new RandomizerImpl(100), Constants.bumrahProbabilities), false);
        playerList.add(bumrah);
        Player nehra = new Player(Constants.nehraName, new RandomOutcome(
                new RandomizerImpl(100), Constants.nehraProbabilities), false);
        playerList.add(nehra);

        //initialize the game stats
        ScoreBoard scoreBoard = new ScoreBoard(Constants.runsRequired, Constants.initialRuns, playerList.iterator());

        //initialize the game instance
        Game game = new Game(playerList, scoreBoard);

        //begin the game
        game.begin();

        //print match commentary ball by ball
        System.out.println(game.toString());

        //print game summary
        System.out.println(game.printSummary());
    }
}
