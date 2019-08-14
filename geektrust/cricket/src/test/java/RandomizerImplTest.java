import com.geektrust.cricket.random.Randomizer;
import com.geektrust.cricket.random.RandomizerImpl;
import org.junit.Assert;
import org.junit.Test;

public class RandomizerImplTest {
    @Test
    public void testBound() {
        Randomizer randomizer = new RandomizerImpl(5);
        Assert.assertTrue(randomizer.next() < 5);
    }
}
