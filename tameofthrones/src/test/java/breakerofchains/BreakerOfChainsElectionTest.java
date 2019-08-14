package breakerofchains;


import com.geektrust.breakerofchains.election.BreakerOfChainsElection;
import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.kingdom.Kingdom;
import com.geektrust.utils.Election;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BreakerOfChainsElectionTest {

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
    public void testWinnerWillBeOneOfTheContestant() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Kingdom spaceKingdom = kingdomFactory.getKingdom("space");
        Kingdom landKingdom = kingdomFactory.getKingdom("land");

        Election election = new BreakerOfChainsElection(kingdomFactory);
        election.addContestant(spaceKingdom);
        election.addContestant(landKingdom);

        election.elect();

        Assert.assertTrue(election.winner() == spaceKingdom || election.winner() == landKingdom);
    }

    @Test
    public void testAlliesMoreThanZeroWhenRulerWins() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();

        Kingdom spaceKingdom = kingdomFactory.getKingdom("space");
        Kingdom landKingdom = kingdomFactory.getKingdom("land");

        Election election = new BreakerOfChainsElection(kingdomFactory);
        election.addContestant(spaceKingdom);
        election.addContestant(landKingdom);

        election.elect();

        Assert.assertTrue(election.winner().numberOfAllies() > 0);
    }
}
