package breakerofchains;

import com.geektrust.breakerofchains.election.BreakerOfChainsElection;
import com.geektrust.breakerofchains.processor.BreakerOfChainsInputProcessor;
import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.kingdom.Kingdom;
import com.geektrust.utils.BaseInputProcessor;
import com.geektrust.utils.Election;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BreakerOfChainsInputProcessorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void init() throws NoSuchKingdomException {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void destroy() throws NoSuchKingdomException {
        System.setOut(new PrintStream(originalOut));
    }

    @Test
    public void testIsQuestion() {
        String question = "Who is the ruler of Southeros?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isQuestion(question), true);

        question = "Allies of Ruler?";
        Assert.assertEquals(inputProcessor.isQuestion(question), true);
    }

    @Test
    public void testNotIsQuestion() {
        String question = "a random question?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isQuestion(question), false);
    }

    @Test
    public void testIsRulerQuestion() {
        String question = "Who is the ruler of Southeros?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isRulerQuestion(question), true);
    }

    @Test
    public void testAlliesQuestion() {
        String question = "Allies of Ruler?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isAlliesQuestion(question), true);
    }

    @Test
    public void testRulerWhenNone() throws NoSuchKingdomException, InvalidInputException {
        String question = "Who is the ruler of Southeros?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "None");
    }

    @Test
    public void testAlliesWhenNone() throws InvalidInputException, NoSuchKingdomException {
        String question = "Allies of Ruler?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "None");
    }

    @Test
    public void testRulerOutputWhenRulerWins() throws InvalidInputException, NoSuchKingdomException {
        String question = "Who is the ruler of Southeros?";
        String message1 = "Air Land";

        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Election election = new BreakerOfChainsElection(kingdomFactory);

        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);

        inputProcessor.processInput(message1);
        outContent.reset();
        inputProcessor.processInput(question);
        Assert.assertTrue(outContent.toString().trim().equals("Land") || outContent.toString().trim().equals("Air"));
    }

    @Test
    public void testShouldHaveAlliesOutputWhenRulerWins() throws InvalidInputException, NoSuchKingdomException {
        String question = "Allies of Ruler?";
        String message1 = "Air Land";

        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Election election = new BreakerOfChainsElection(kingdomFactory);

        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);

        inputProcessor.processInput(message1);
        outContent.reset();
        inputProcessor.processInput(question);
        Assert.assertTrue(outContent.toString().trim().length() > 0);
    }

    @Test(expected = InvalidInputException.class)
    public void testContestantsShouldBeMoreThanOne() throws InvalidInputException, NoSuchKingdomException {
        String input = "Air";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new BreakerOfChainsElection(kingdomFactory);
        BaseInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(election, kingdomFactory);
        inputProcessor.processInput(input);
    }
}
