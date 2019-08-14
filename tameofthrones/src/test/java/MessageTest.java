import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.kingdom.Kingdom;
import com.geektrust.message.Message;
import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    @Test
    public void testSender() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Kingdom sender = kingdomFactory.getKingdom("air");
        Message message = new Message(null, sender, null);
        Assert.assertEquals(message.getSender(), sender);
    }

    @Test
    public void testReceiver() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Kingdom receiver = kingdomFactory.getKingdom("air");
        Message message = new Message(null, null, receiver);
        Assert.assertEquals(message.getReceiver(), receiver);
    }

    @Test
    public void testIsValid() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Kingdom receiver = kingdomFactory.getKingdom("air");
        Message message = new Message("owl", null, receiver);
        Assert.assertEquals(message.isValid(), true);
    }

    @Test
    public void testIsNotValid() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Kingdom receiver = kingdomFactory.getKingdom("air");
        Message message = new Message("xyz", null, receiver);
        Assert.assertEquals(message.isValid(), false);
    }
}
