import com.geektrust.kingdom.Kingdom;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class KingdomTest {
    @Test
    public void testGetEmblem() {
        Kingdom kingdom = new Kingdom("emb", "nam");
        Assert.assertEquals(kingdom.getEmblem(), "emb");
    }

    @Test
    public void testGetName() {
        Kingdom kingdom = new Kingdom("emb", "nam");
        Assert.assertEquals(kingdom.getName(), "nam");
    }

    @Test
    public void testNumberOfAllies() {
        Kingdom kingdom = new Kingdom("emb", "nam");
        Assert.assertEquals(kingdom.numberOfAllies(), 0);

        Kingdom ally = Mockito.mock(Kingdom.class);
        kingdom.addAlly(ally);
        Assert.assertEquals(kingdom.numberOfAllies(), 1);
    }

    @Test
    public void testResetAllies() {
        Kingdom kingdom = new Kingdom("emb", "nam");
        Kingdom ally = Mockito.mock(Kingdom.class);
        kingdom.addAlly(ally);
        Assert.assertEquals(kingdom.numberOfAllies(), 1);

        kingdom.resetAllies();
        Assert.assertEquals(kingdom.numberOfAllies(), 0);
    }

    @Test
    public void testAlliesNames() {
        Kingdom kingdom = new Kingdom("emb", "nam");
        Kingdom ally1 = new Kingdom("emb1", "nam1");
        kingdom.addAlly(ally1);
        Kingdom ally2 = new Kingdom("emb2", "nam2");
        kingdom.addAlly(ally2);

        Assert.assertArrayEquals(kingdom.getAlliesNames().toArray(), new String[] {"nam1", "nam2"});
    }

    @Test
    public void testKingdomEquality() {
        Kingdom kingdom1 = new Kingdom("emb", "nam");
        Kingdom kingdom2 = new Kingdom("emb2", "nam2");
        Assert.assertNotEquals(kingdom1, kingdom2);

        Kingdom kingdom3 = new Kingdom("emb3", "nam");
        Assert.assertEquals(kingdom1, kingdom3);
    }
}
