import com.geektrust.cricket.player.PlayerStat;
import com.geektrust.cricket.utils.BallOutcomesEnum;
import org.junit.Assert;
import org.junit.Test;

public class PlayerStatTest {
    @Test
    public void testUpdateStatIsBattingWhenOut() {
        PlayerStat stat = new PlayerStat(true);
        stat.updateStat(BallOutcomesEnum.OUT);
        Assert.assertFalse(stat.toString().contains("*"));
    }

    @Test
    public void testUpdateStatIsBattingWhenNotOut() {
        PlayerStat stat = new PlayerStat(true);
        stat.updateStat(BallOutcomesEnum.DOT_BALL);
        Assert.assertTrue(stat.toString().contains("*"));
    }

    @Test
    public void testUpdateStatRunScoredWhenDotBall() {
        PlayerStat stat = new PlayerStat(true);
        stat.updateStat(BallOutcomesEnum.DOT_BALL);
        Assert.assertTrue(stat.toString().contains(" - 0* (1 ball)"));
    }

    @Test
    public void testUpdateStatRunScoredWhenRunScored() {
        PlayerStat stat = new PlayerStat(true);
        stat.updateStat(BallOutcomesEnum.TWO_RUNS);
        Assert.assertTrue(stat.toString().contains(" - 2* (1 ball)"));
    }

    @Test
    public void testWhenNoBallsPlayedYet() {
        PlayerStat stat = new PlayerStat(true);
        Assert.assertTrue(stat.toString().contains(" - 0* (0 balls)"));
    }

    @Test
    public void testWhenOneBallPlayed() {
        PlayerStat stat = new PlayerStat(true);
        stat.updateStat(BallOutcomesEnum.DOT_BALL);
        Assert.assertTrue(stat.toString().contains(" - 0* (1 ball)"));
    }
}
