package goldencrown;


import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.goldencrown.election.GoldenCrownElection;
import com.geektrust.goldencrown.processor.GoldenCrownInputProcessor;
import com.geektrust.kingdom.Kingdom;
import com.geektrust.utils.BaseInputProcessor;
import com.geektrust.utils.Election;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GoldenCrownInputProcessorTest {

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
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isQuestion(question), true);

        question = "Allies of Ruler?";
        Assert.assertEquals(inputProcessor.isQuestion(question), true);
    }

    @Test
    public void testNotIsQuestion() {
        String question = "a random question?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isQuestion(question), false);
    }

    @Test
    public void testIsRulerQuestion() {
        String question = "Who is the ruler of Southeros?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isRulerQuestion(question), true);
    }

    @Test
    public void testAlliesQuestion() {
        String question = "Allies of Ruler?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        Assert.assertEquals(inputProcessor.isAlliesQuestion(question), true);
    }

    @Test
    public void testRulerWhenNone() throws InvalidInputException, NoSuchKingdomException {
        String question = "Who is the ruler of Southeros?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "None");
    }

    @Test
    public void testAlliesWhenNone() throws InvalidInputException, NoSuchKingdomException {
        String question = "Allies of Ruler?";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "None");
    }

    @Test
    public void testAlliesWhenNoRuler() throws InvalidInputException, NoSuchKingdomException {
        String question = "Allies of Ruler?";
        String message = "Air, low";

        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Kingdom spaceKingdom = kingdomFactory.getKingdom("Space");

        Election election = new GoldenCrownElection();
        election.addContestant(spaceKingdom);

        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);

        inputProcessor.processInput(message);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "None");
    }

    @Test
    public void testWhenRulerWins() throws InvalidInputException, NoSuchKingdomException {
        String question = "Who is the ruler of Southeros?";
        String message1 = "Air, low";
        String message2 = "Land, a1d22n333a4444p";
        String message3 = "Ice, zmzmzmzaztzozh";

        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Kingdom spaceKingdom = kingdomFactory.getKingdom("Space");

        Election election = new GoldenCrownElection();
        election.addContestant(spaceKingdom);

        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);

        inputProcessor.processInput(message1);
        inputProcessor.processInput(message2);
        inputProcessor.processInput(message3);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "King Shan");
    }

    @Test
    public void testAlliesWhenRulerWins() throws InvalidInputException, NoSuchKingdomException {
        String question = "Allies of Ruler?";
        String message1 = "Air, low";
        String message2 = "Land, a1d22n333a4444p";
        String message3 = "Ice, zmzmzmzaztzozh";

        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Kingdom spaceKingdom = kingdomFactory.getKingdom("Space");

        Election election = new GoldenCrownElection();
        election.addContestant(spaceKingdom);

        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);

        inputProcessor.processInput(message1);
        inputProcessor.processInput(message2);
        inputProcessor.processInput(message3);
        inputProcessor.processInput(question);
        Assert.assertEquals(outContent.toString().trim(), "Ice, Air, Land");
    }

    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws InvalidInputException, NoSuchKingdomException {
        String input = "Air";
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        BaseInputProcessor inputProcessor = new GoldenCrownInputProcessor(election, kingdomFactory);
        inputProcessor.processInput(input);
    }
}
