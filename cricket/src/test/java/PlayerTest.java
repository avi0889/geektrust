import com.geektrust.cricket.outcome.RandomOutcome;
import com.geektrust.cricket.player.Player;
import com.geektrust.cricket.utils.BallOutcomesEnum;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PlayerTest {
    @Test
    public void testOutcomeWhenPlay() {
        RandomOutcome randomOutcome = Mockito.mock(RandomOutcome.class);
        Mockito.when(randomOutcome.getOutcome()).thenReturn(1);
        Player player = new Player("", randomOutcome, true);
        Assert.assertEquals(player.play(), BallOutcomesEnum.ONE_RUN);
    }
}
