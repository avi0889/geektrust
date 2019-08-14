package goldencrown;


import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.goldencrown.election.GoldenCrownElection;
import com.geektrust.utils.Constants;
import com.geektrust.utils.Election;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoldenCrownElectionTest {

    @Test
    public void testContestantWinningNeedsAtLeastThreeAllies() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Election election = new GoldenCrownElection();
        election.addContestant(kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName));

        kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName).addAlly(kingdomFactory.getKingdom("land"));
        election.elect();
        Assert.assertEquals(election.winner(), null);

        kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName).addAlly(kingdomFactory.getKingdom("air"));
        election.elect();
        Assert.assertEquals(election.winner(), null);

        kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName).addAlly(kingdomFactory.getKingdom("water"));
        election.elect();
        Assert.assertEquals(election.winner(), kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName));
    }
}
