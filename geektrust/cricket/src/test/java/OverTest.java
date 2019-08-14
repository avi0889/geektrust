import com.geektrust.cricket.game.Over;
import com.geektrust.cricket.utils.BallOutcomesEnum;
import org.junit.Assert;
import org.junit.Test;

public class OverTest {

    @Test
    public void testStrikeChangeOnNonLastBallOfOverWhenEvenRunsScored() {
        Over over = new Over(0);
        over.updateBall(BallOutcomesEnum.TWO_RUNS, "");
        Assert.assertEquals(over.isStrikeChange(), false);
    }

    @Test
    public void testStrikeChangeOnNonLastBallOfOverWhenOddRunsScored() {
        Over over = new Over(0);
        over.updateBall(BallOutcomesEnum.ONE_RUN, "");
        Assert.assertEquals(over.isStrikeChange(), true);

    }

    @Test
    public void testStrikeChangeOnNonLastBallOfOverWhenOut() {
        Over over = new Over(0);
        over.updateBall(BallOutcomesEnum.OUT, "");
        Assert.assertEquals(over.isStrikeChange(), false);

    }

    @Test
    public void testStrikeChangeOnLastBallOfOverWhenEvenRunsScored() {
        Over over = new Over(0);
        //set the first five balls to anything, doesn't matter in this test
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        //score even runs on last ball
        over.updateBall(BallOutcomesEnum.TWO_RUNS, "");
        Assert.assertEquals(over.isStrikeChange(), true);
    }

    @Test
    public void testStrikeChangeOnLastBallOfOverWhenOddRunsScored() {
        Over over = new Over(0);
        //set the first five balls to anything, doesn't matter in this test
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        //score even runs on last ball
        over.updateBall(BallOutcomesEnum.ONE_RUN, "");
        Assert.assertEquals(over.isStrikeChange(), false);
    }

    @Test
    public void testStrikeChangeOnLastBallOfOverWhenOut() {
        Over over = new Over(0);
        //set the first five balls to anything, doesn't matter in this test
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        //score even runs on last ball
        over.updateBall(BallOutcomesEnum.OUT, "");
        Assert.assertEquals(over.isStrikeChange(), true);
    }

    @Test
    public void testIsNotOverComplete() {
        Over over = new Over(0);
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        Assert.assertEquals(over.isComplete(), false);
    }

    @Test
    public void testIsOverComplete() {
        Over over = new Over(0);
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        Assert.assertEquals(over.isComplete(), true);
    }

    @Test
    public void testBallRemainingInOver() {
        Over over = new Over(0);
        over.updateBall(BallOutcomesEnum.DOT_BALL, "");
        Assert.assertEquals(over.ballRemaining(), 5);
    }
}
