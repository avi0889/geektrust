import com.geektrust.cricket.game.ScoreBoard;
import com.geektrust.cricket.outcome.RandomOutcome;
import com.geektrust.cricket.player.Player;
import com.geektrust.cricket.random.RandomizerImpl;
import com.geektrust.cricket.utils.BallOutcomesEnum;
import com.geektrust.cricket.utils.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardTest {
    @Test
    public void testInitialStriker() {
        List<Player> list = new ArrayList<>();
        Player p1 = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        list.add(p1);
        Player p2 = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        list.add(p2);
        ScoreBoard scoreBoard = new ScoreBoard(10, 0, list.iterator());
        Assert.assertEquals(scoreBoard.getStriker(), p1);
    }

    @Test
    public void testStrikerWhenChange() {
        List<Player> list = new ArrayList<>();
        Player p1 = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        list.add(p1);
        Player p2 = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        list.add(p2);
        ScoreBoard scoreBoard = new ScoreBoard(10, 0, list.iterator());
        scoreBoard.updateStat(BallOutcomesEnum.ONE_RUN);
        Assert.assertEquals(scoreBoard.getStriker(), p2);
    }

    @Test
    public void testStrikerWhenOut() {
        List<Player> list = new ArrayList<>();
        Player p1 = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        list.add(p1);
        Player p2 = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        list.add(p2);
        Player p3 = new Player(Constants.bumrahName, new RandomOutcome(
                new RandomizerImpl(100), Constants.bumrahProbabilities), true);
        list.add(p3);
        ScoreBoard scoreBoard = new ScoreBoard(10, 0, list.iterator());
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        Assert.assertEquals(scoreBoard.getStriker(), p3);
    }

    @Test
    public void testWin() {
        List<Player> list = new ArrayList<>();
        Player p1 = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        list.add(p1);
        Player p2 = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        list.add(p2);
        ScoreBoard scoreBoard = new ScoreBoard(1, 0, list.iterator());
        scoreBoard.updateStat(BallOutcomesEnum.TWO_RUNS);
        Assert.assertEquals(scoreBoard.isWon(), true);
    }

    @Test
    public void testLoss() {
        List<Player> list = new ArrayList<>();
        Player p1 = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        list.add(p1);
        Player p2 = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        list.add(p2);
        Player p3 = new Player(Constants.bumrahName, new RandomOutcome(
                new RandomizerImpl(100), Constants.bumrahProbabilities), true);
        list.add(p3);
        Player p4 = new Player(Constants.nehraName, new RandomOutcome(
                new RandomizerImpl(100), Constants.nehraProbabilities), true);
        list.add(p4);

        ScoreBoard scoreBoard = new ScoreBoard(1, 0, list.iterator());
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        Assert.assertEquals(scoreBoard.isLost(), true);
    }

    @Test
    public void testTie() {
        List<Player> list = new ArrayList<>();
        Player p1 = new Player(Constants.kohliName, new RandomOutcome(
                new RandomizerImpl(100), Constants.kohliProbabilities), true);
        list.add(p1);
        Player p2 = new Player(Constants.dhoniName, new RandomOutcome(
                new RandomizerImpl(100), Constants.dhoniProbabilities), true);
        list.add(p2);
        Player p3 = new Player(Constants.bumrahName, new RandomOutcome(
                new RandomizerImpl(100), Constants.bumrahProbabilities), true);
        list.add(p3);
        Player p4 = new Player(Constants.nehraName, new RandomOutcome(
                new RandomizerImpl(100), Constants.nehraProbabilities), true);
        list.add(p4);

        ScoreBoard scoreBoard = new ScoreBoard(0, 0, list.iterator());
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        scoreBoard.updateStat(BallOutcomesEnum.OUT);
        Assert.assertEquals(scoreBoard.isTied(), true);
    }
}
