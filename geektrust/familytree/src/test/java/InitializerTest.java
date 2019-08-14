import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.exception.NoSuchRelationshipException;
import com.geektrust.familytree.initialize.Initializer;
import com.geektrust.familytree.relations.Relation;
import com.geektrust.familytree.relations.RelationFactory;
import com.geektrust.familytree.repository.DescendantRepository;
import com.geektrust.familytree.repository.PartnerRepository;
import com.geektrust.familytree.repository.RelativesRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InitializerTest {

    private Map<String, Person> allPersonsMap;
    private RelativesRepository<EDescendant, String> descendantsRepository;
    private RelativesRepository<EPartner, String> partnersRepository;

    @Before
    public void init() {
        this.descendantsRepository = new DescendantRepository(new HashMap<>());
        this.partnersRepository = new PartnerRepository(new HashMap<>());
        allPersonsMap = new Initializer(partnersRepository, descendantsRepository).init();
    }

    @Test
    public void testPartnersInInitialState() {
        Assert.assertEquals(partnersRepository.findOne("Shan").getPartner().getName(), "Anga");
        Assert.assertEquals(partnersRepository.findOne("Chit").getPartner().getName(), "Amba");
        Assert.assertNull(partnersRepository.findOne("Ish"));
        Assert.assertEquals(partnersRepository.findOne("Vich").getPartner().getName(), "Lika");
        Assert.assertEquals(partnersRepository.findOne("Aras").getPartner().getName(), "Chitra");
        Assert.assertEquals(partnersRepository.findOne("Vyan").getPartner().getName(), "Satya");
        Assert.assertEquals(partnersRepository.findOne("Jaya").getPartner().getName(), "Dritha");
        Assert.assertNull(partnersRepository.findOne("Tritha"));
        Assert.assertNull(partnersRepository.findOne("Vritha"));
        Assert.assertNull(partnersRepository.findOne("Vila"));
        Assert.assertNull(partnersRepository.findOne("Chika"));
        Assert.assertEquals(partnersRepository.findOne("Arit").getPartner().getName(), "Jnki");
        Assert.assertNull(partnersRepository.findOne("Ahit"));
        Assert.assertEquals(partnersRepository.findOne("Asva").getPartner().getName(), "Satvy");
        Assert.assertEquals(partnersRepository.findOne("Vyas").getPartner().getName(), "Krpi");
        Assert.assertNull(partnersRepository.findOne("Atya"));
        Assert.assertNull(partnersRepository.findOne("Yodhan"));
        Assert.assertNull(partnersRepository.findOne("Laki"));
        Assert.assertNull(partnersRepository.findOne("Lavnya"));
        Assert.assertNull(partnersRepository.findOne("Vasa"));
        Assert.assertNull(partnersRepository.findOne("Kriya"));
        Assert.assertNull(partnersRepository.findOne("Krithi"));
    }

    @Test
    public void testDescendancyCountInInitialStater() {
        Assert.assertEquals(descendantsRepository.findOne("Anga").getChildren().size(), 5);
        Assert.assertEquals(descendantsRepository.findOne("Amba").getChildren().size(), 3);
        Assert.assertEquals(descendantsRepository.findOne("Lika").getChildren().size(), 2);
        Assert.assertEquals(descendantsRepository.findOne("Chitra").getChildren().size(), 2);
        Assert.assertEquals(descendantsRepository.findOne("Satya").getChildren().size(), 3);
        Assert.assertEquals(descendantsRepository.findOne("Dritha").getChildren().size(), 1);
        Assert.assertEquals(descendantsRepository.findOne("Jnki").getChildren().size(), 2);
        Assert.assertEquals(descendantsRepository.findOne("Satvy").getChildren().size(), 1);
        Assert.assertEquals(descendantsRepository.findOne("Krpi").getChildren().size(), 2);
    }

    @Test
    public void testSiblingsInInitialState() {
        String siblingRelationshipName = "Siblings";
        Relation relation = null;
        try {
            relation = RelationFactory.getRelationship(siblingRelationshipName,
                    descendantsRepository, partnersRepository);
        } catch (NoSuchRelationshipException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Chit")).size(), 4);

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Dritha")).size(), 2);

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Vila")).size(), 1);

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Jnki")).size(), 1);

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Asva")).size(), 2);

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Laki")).size(), 1);

        Assert.assertEquals(relation.getRelatives(allPersonsMap.get("Kriya")).size(), 1);
    }
}
