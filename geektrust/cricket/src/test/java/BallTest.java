import com.geektrust.cricket.game.Ball;
import com.geektrust.cricket.utils.BallOutcomesEnum;
import org.junit.Assert;
import org.junit.Test;

public class BallTest {

    @Test
    public void testStrikeChangeWhenOddRunsScoredAndNotLastBallOfOver() {
        Ball ball = new Ball("", 1, BallOutcomesEnum.ONE_RUN);
        Assert.assertEquals(ball.shouldBatsmanChangeStrike(), true);
    }

    @Test
    public void testStrikeChangeWhenEvenRunsScoredAndNotLastBallOfOver() {
        Ball ball = new Ball("", 1, BallOutcomesEnum.TWO_RUNS);
        Assert.assertEquals(ball.shouldBatsmanChangeStrike(), false);
    }

    @Test
    public void testStrikeChangeWhenOutAndNotLastBallOfOver() {
        Ball ball = new Ball("", 1, BallOutcomesEnum.OUT);
        Assert.assertEquals(ball.shouldBatsmanChangeStrike(), false);
    }

    @Test
    public void testWhenNotLastBallOfOver() {
        Ball ball = new Ball("", 5, BallOutcomesEnum.DOT_BALL);
        Assert.assertEquals(ball.isLastBallOfOver(), false);
    }

    @Test
    public void testWhenLastBallOfOver() {
        Ball ball = new Ball("", 6, BallOutcomesEnum.DOT_BALL);
        Assert.assertEquals(ball.isLastBallOfOver(), true);
    }
}
