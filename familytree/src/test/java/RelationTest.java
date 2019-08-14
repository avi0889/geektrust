import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.parse.InputParser;
import com.geektrust.familytree.relations.*;
import com.geektrust.familytree.repository.RelativesRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class RelationTest {

    private InputParser inputParser;
    private Map<String, Person> personMap;
    private RelativesRepository<EDescendant, String> descendantsRepository;
    private RelativesRepository<EPartner, String> partnersRepository;

    @Before
    public void init() {
        inputParser = Mockito.mock(InputParser.class);
        personMap = Mockito.mock(Map.class);
        descendantsRepository = Mockito.mock(RelativesRepository.class);
        partnersRepository = Mockito.mock(RelativesRepository.class);
    }

    /**
     * tests for ChildRelation class
     */
    @Test
    public void testGetSonFromMotherRelationship() {
        Person mother = new Person("y", "female", null);
        EDescendant eDescendant = new EDescendant(mother);
        Person maleChild = new Person("xm", "male", mother);
        eDescendant.addChild(maleChild);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant);

        Relation sonRelation = new ChildRelation(true, partnersRepository, descendantsRepository);
        Assert.assertEquals(sonRelation.getRelatives(mother), new ArrayList<>(Arrays.asList(maleChild)));
    }

    @Test
    public void testGetSonFromFatherRelationship() {
        Person father = new Person("z", "male", null);
        Person mother = new Person("y", "female", null);
        EPartner ePartner = new EPartner(mother, father);
        Mockito.when(partnersRepository.findOne(father.getName())).thenReturn(ePartner);

        EDescendant eDescendant = new EDescendant(mother);
        Person maleChild = new Person("xm", "male", mother);
        eDescendant.addChild(maleChild);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant);

        Relation sonRelation = new ChildRelation(true, partnersRepository, descendantsRepository);
        Assert.assertEquals(sonRelation.getRelatives(mother), new ArrayList<>(Arrays.asList(maleChild)));
    }

    @Test
    public void testGetDaughterFromMotherRelationship() {
        Person mother = new Person("y", "female", null);
        EDescendant eDescendant = new EDescendant(mother);
        Person femaleChild = new Person("xf", "female", mother);
        eDescendant.addChild(femaleChild);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant);

        Relation daughterRelation = new ChildRelation(false, partnersRepository, descendantsRepository);
        Assert.assertEquals(daughterRelation.getRelatives(mother), new ArrayList<>(Arrays.asList(femaleChild)));
    }

    @Test
    public void testGetDaughterFromFatherRelationship() {
        Person father = new Person("z", "male", null);
        Person mother = new Person("y", "female", null);
        EPartner ePartner = new EPartner(mother, father);
        Mockito.when(partnersRepository.findOne(father.getName())).thenReturn(ePartner);

        EDescendant eDescendant = new EDescendant(mother);
        Person femaleChild = new Person("xf", "female", mother);
        eDescendant.addChild(femaleChild);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant);

        Relation daughterRelation = new ChildRelation(false, partnersRepository, descendantsRepository);
        Assert.assertEquals(daughterRelation.getRelatives(mother), new ArrayList<>(Arrays.asList(femaleChild)));
    }

    /**
     * tests for SiblingRelation class
     */
    @Test
    public void testGetSiblingsRelationship() {
        Person mother = new Person("y", "female", null);
        EDescendant eDescendant = new EDescendant(mother);
        Person maleChild = new Person("xm", "male", mother);
        Person femaleChild = new Person("xf", "female", mother);
        Person child = new Person("x", "male", mother);
        eDescendant.addChild(maleChild);
        eDescendant.addChild(femaleChild);
        eDescendant.addChild(child);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Assert.assertEquals(siblingRelation.getRelatives(child),
                new ArrayList<>(Arrays.asList(femaleChild, maleChild)));
    }

    /**
     * tests for InLawRelation class
     */
    @Test
    public void testGetBrothersInLawRelationship() {
        Person mother1 = new Person("y", "female", null);
        Person person = new Person("a", "male", mother1);
        Person sister = new Person("d", "female", mother1);
        Person sisterPartner = new Person("e", "male", null);
        EPartner ePartner1 = new EPartner(sister, sisterPartner);
        Mockito.when(partnersRepository.findOne("d")).thenReturn(ePartner1);
        EDescendant eDescendant1 = new EDescendant(mother1);
        eDescendant1.addChild(person);
        eDescendant1.addChild(sister);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant1);

        Person mother2 = new Person("z", "female", null);
        Person partner = new Person("b", "female", mother2);
        EPartner ePartner2 = new EPartner(person, partner);
        Mockito.when(partnersRepository.findOne("a")).thenReturn(ePartner2);
        Person partnerBrother = new Person("c", "male", mother2);
        EDescendant eDescendant2 = new EDescendant(mother2);
        eDescendant2.addChild(partner);
        eDescendant2.addChild(partnerBrother);
        Mockito.when(descendantsRepository.findOne("z")).thenReturn(eDescendant2);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Relation brothersInLawRelation = new InLawRelation(true, partnersRepository, siblingRelation);
        Assert.assertEquals(brothersInLawRelation.getRelatives(person),
                new ArrayList<>(Arrays.asList(partnerBrother, sisterPartner)));
    }

    @Test
    public void testGetSistersInLawRelationship() {
        Person mother1 = new Person("y", "female", null);
        Person person = new Person("a", "male", mother1);
        Person brother = new Person("d", "male", mother1);
        Person brotherPartner = new Person("e", "female", null);
        EPartner ePartner1 = new EPartner(brother, brotherPartner);
        Mockito.when(partnersRepository.findOne("d")).thenReturn(ePartner1);
        EDescendant eDescendant1 = new EDescendant(mother1);
        eDescendant1.addChild(person);
        eDescendant1.addChild(brother);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant1);

        Person mother2 = new Person("z", "female", null);
        Person partner = new Person("b", "female", mother2);
        EPartner ePartner2 = new EPartner(person, partner);
        Mockito.when(partnersRepository.findOne("a")).thenReturn(ePartner2);
        Person partnerSister = new Person("c", "female", mother2);
        EDescendant eDescendant2 = new EDescendant(mother2);
        eDescendant2.addChild(partner);
        eDescendant2.addChild(partnerSister);
        Mockito.when(descendantsRepository.findOne("z")).thenReturn(eDescendant2);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Relation brothersInLawRelation = new InLawRelation(false, partnersRepository, siblingRelation);
        Assert.assertEquals(brothersInLawRelation.getRelatives(person),
                new ArrayList<>(Arrays.asList(partnerSister, brotherPartner)));
    }

    /**
     * tests for PiblingRelation class
     */
    @Test
    public void testGetMaternalAuntRelationship() {
        Person grandMother = new Person("gm", "female", null);
        Person mother = new Person("y", "female", grandMother);
        Person aunt = new Person("a", "female", grandMother);
        EDescendant eDescendant1 = new EDescendant(grandMother);
        eDescendant1.addChild(mother);
        eDescendant1.addChild(aunt);
        Mockito.when(descendantsRepository.findOne("gm")).thenReturn(eDescendant1);

        Person person = new Person("x", "male", mother);
        EDescendant eDescendant2 = new EDescendant(mother);
        eDescendant2.addChild(person);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant2);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Relation maternalAuntRelation = new PiblingRelation(false, false, siblingRelation, partnersRepository);
        Assert.assertEquals(maternalAuntRelation.getRelatives(person), new ArrayList<>(Arrays.asList(aunt)));
    }

    @Test
    public void testGetMaternalUncleRelationship() {
        Person grandMother = new Person("gm", "female", null);
        Person mother = new Person("y", "female", grandMother);
        Person uncle = new Person("a", "male", grandMother);
        EDescendant eDescendant1 = new EDescendant(grandMother);
        eDescendant1.addChild(mother);
        eDescendant1.addChild(uncle);
        Mockito.when(descendantsRepository.findOne("gm")).thenReturn(eDescendant1);

        Person person = new Person("x", "male", mother);
        EDescendant eDescendant2 = new EDescendant(mother);
        eDescendant2.addChild(person);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(eDescendant2);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Relation maternalUncleRelation = new PiblingRelation(false, true, siblingRelation, partnersRepository);
        Assert.assertEquals(maternalUncleRelation.getRelatives(person), new ArrayList<>(Arrays.asList(uncle)));
    }

    @Test
    public void testGetPaternalUncleRelationship() {
        Person grandMother = new Person("gm", "female", null);
        Person father = new Person("f", "male", grandMother);
        Person uncle = new Person("a", "male", grandMother);
        EDescendant eDescendant1 = new EDescendant(grandMother);
        eDescendant1.addChild(father);
        eDescendant1.addChild(uncle);
        Mockito.when(descendantsRepository.findOne("gm")).thenReturn(eDescendant1);

        Person mother = new Person("y", "female", null);
        EPartner ePartner = new EPartner(mother, father);
        Mockito.when(partnersRepository.findOne("y")).thenReturn(ePartner);

        Person person = new Person("x", "male", mother);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Relation paternalUncleRelation = new PiblingRelation(true, true, siblingRelation, partnersRepository);
        Assert.assertEquals(paternalUncleRelation.getRelatives(person), new ArrayList<>(Arrays.asList(uncle)));
    }

    @Test
    public void testGetPaternalAuntRelationship() {
        Person grandMother = new Person("gm", "female", null);
        Person father = new Person("f", "male", grandMother);
        Person aunt = new Person("a", "female", grandMother);
        EDescendant eDescendant1 = new EDescendant(grandMother);
        eDescendant1.addChild(father);
        eDescendant1.addChild(aunt);
        Mockito.when(descendantsRepository.findOne("gm")).thenReturn(eDescendant1);

        Person mother = new Person("y", "female", null);
        EPartner ePartner = new EPartner(mother, father);
        Mockito.when(partnersRepository.findOne("y")).thenReturn(ePartner);

        Person person = new Person("x", "male", mother);

        Relation siblingRelation = new SiblingRelation(descendantsRepository);
        Relation paternalAuntRelation = new PiblingRelation(true, false, siblingRelation, partnersRepository);
        Assert.assertEquals(paternalAuntRelation.getRelatives(person), new ArrayList<>(Arrays.asList(aunt)));
    }

//    @Test
//    public void testGetRelationshipWhenNoneExists() {
//        String[] commands = new String[] {"GET_RELATIONSHIP", "Vasa", "Siblings"};
//        inputParser.execute(commands);
//        Assert.assertEquals(outContent.toString().trim(), "NONE");
//    }
}
