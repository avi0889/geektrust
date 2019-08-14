import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.kingdom.Kingdom;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class KingdomFactoryImplTest {

    @Test(expected = NoSuchKingdomException.class)
    public void testGetInvalidKingdom() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        kingdomFactory.getKingdom("some random kingdom");
    }

    @Test
    public void testGetTotalNumberOfKingdoms() {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Assert.assertEquals(kingdomFactory.totalNumberOfKingdoms(), 6);
    }

    @Test
    public void testGetAllKingdoms() throws NoSuchKingdomException {
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        Assert.assertArrayEquals(kingdomFactory.getAllKingdoms().toArray(),
                new Kingdom[]{kingdomFactory.getKingdom("land"), kingdomFactory.getKingdom("fire"),
                        kingdomFactory.getKingdom("ice"), kingdomFactory.getKingdom("air"),
                        kingdomFactory.getKingdom("water"), kingdomFactory.getKingdom("space")});
    }
}
