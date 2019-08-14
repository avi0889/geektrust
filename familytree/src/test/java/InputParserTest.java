import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.exception.NoSuchRelationshipException;
import com.geektrust.familytree.initialize.Initializer;
import com.geektrust.familytree.parse.InputParser;
import com.geektrust.familytree.repository.RelativesRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockingDetails;

public class InputParserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private InputParser inputParser;
    private Map<String, Person> personMap;
    private RelativesRepository<EDescendant, String> descendantsRepository;
    private RelativesRepository<EPartner, String> partnersRepository;

    @Before
    public void init() {
        System.setOut(new PrintStream(outContent));
        inputParser = Mockito.mock(InputParser.class);
        personMap = Mockito.mock(Map.class);
        descendantsRepository = Mockito.mock(RelativesRepository.class);
        partnersRepository = Mockito.mock(RelativesRepository.class);
    }

    @After
    public void destroy() {
        System.setOut(new PrintStream(originalOut));
    }

    @Test
    public void testPersonAdditionWhenMotherNotFound() {
        String[] commands = new String[] {"ADD_CHILD", "y", "x", "male"};
        inputParser = new InputParser(personMap, descendantsRepository, partnersRepository);
        inputParser.execute(commands);
        Assert.assertEquals(outContent.toString().trim(), "PERSON_NOT_FOUND");
    }

    @Test
    public void testPersonAdditionWhenMotherIsMale() {
        String[] commands = new String[] {"ADD_CHILD", "y", "x", "male"};
        Mockito.when(personMap.get("y")).thenReturn(new Person("y", "male", null));
        inputParser = new InputParser(personMap, descendantsRepository, partnersRepository);
        inputParser.execute(commands);
        Assert.assertEquals(outContent.toString().trim(), "CHILD_ADDITION_FAILED");
    }

    @Test
    public void testSuccessfulPersonAddition() {
        String[] commands = new String[] {"ADD_CHILD", "y", "x", "male"};
        Person mother = new Person("y", "female", null);
        Mockito.when(personMap.get("y")).thenReturn(mother);
        Mockito.when(descendantsRepository.findOne("y")).thenReturn(new EDescendant(mother));
        inputParser = new InputParser(personMap, descendantsRepository, partnersRepository);
        inputParser.execute(commands);
        Assert.assertEquals(outContent.toString().trim(), "CHILD_ADDITION_SUCCEEDED");
    }

    @Test
    public void testGetRelationshipWhenPersonNotExists() {
        String[] commands = new String[] {"GET_RELATIONSHIP", "y", "Son"};
        inputParser = new InputParser(personMap, descendantsRepository, partnersRepository);
        inputParser.execute(commands);
        Assert.assertEquals(outContent.toString().trim(), "PERSON_NOT_FOUND");
    }

    @Test
    public void testGetRelationshipWhenNoRelativeFound() {
        String[] commands = new String[] {"GET_RELATIONSHIP", "y", "Son"};
        Person mother = new Person("y", "female", null);
        Mockito.when(personMap.get("y")).thenReturn(mother);
        inputParser = new InputParser(personMap, descendantsRepository, partnersRepository);
        inputParser.execute(commands);
        Assert.assertEquals(outContent.toString().trim(), "NONE");
    }

    @Test
    public void testGetRelationshipWhenRelativeFound() {
        String[] commands = new String[] {"GET_RELATIONSHIP", "y", "Son"};
        Person mother = new Person("y", "female", null);
        EDescendant eDescendant = new EDescendant(mother);
        eDescendant.addChild(new Person("x", "male", mother));
        Mockito.when(personMap.get("y")).thenReturn(mother);
        Mockito.when(descendantsRepository.findOne(mother.getName())).thenReturn(eDescendant);
        inputParser = new InputParser(personMap, descendantsRepository, partnersRepository);
        inputParser.execute(commands);
        Assert.assertEquals(outContent.toString().trim(), "x");
    }
}
