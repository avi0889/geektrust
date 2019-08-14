package breakerofchains;


import com.geektrust.breakerofchains.ballot.Ballot;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

public class BallotTest {
    @Test
    public void testPickNMessages() {
        Ballot ballot = new Ballot();
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        //send one message which is basically 5 messages inside ballot since 5 receivers are mentioned
        ballot.addMessages("msg1", null, kingdomFactory.getAllKingdoms());
        Assert.assertEquals(ballot.pickNRandomMessages(2).size(), 2);
    }
}
