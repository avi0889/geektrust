import com.geektrust.cricket.outcome.Outcome;
import com.geektrust.cricket.outcome.RandomOutcome;
import com.geektrust.cricket.random.Randomizer;
import com.geektrust.cricket.random.RandomizerImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomOutcomeTest {
    @Test
    public void testOutcomeWhenOneProbability() {
        List<Integer> probabilities = new ArrayList<>();
        probabilities.add(100);
        Randomizer randomizer = new RandomizerImpl(100);
        Outcome outcome = new RandomOutcome(randomizer, probabilities);
        Assert.assertEquals(outcome.getOutcome(), 0);
    }

    @Test
    public void testOutcomeWhenTwoProbabilities() {
        List<Integer> probabilities = new ArrayList<>();
        probabilities.add(50);
        probabilities.add(50);
        Randomizer randomizer = new RandomizerImpl(100);
        Outcome outcome = new RandomOutcome(randomizer, probabilities);
        Assert.assertTrue(new ArrayList<Number>(Arrays.asList(0, 1)).contains(outcome.getOutcome()));
    }
}
