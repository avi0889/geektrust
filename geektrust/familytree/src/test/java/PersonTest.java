import com.geektrust.familytree.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonTest {

    @Test
    public void testPerson() {
        Person mother = Mockito.mock(Person.class);

        Person person = new Person("name", "male", mother);
        Assert.assertEquals(person.toString(), "name");
        Assert.assertEquals(person.isMale(), true);
        Assert.assertEquals(person.getMother(), mother);
    }

    @Test
    public void testPersonEquality() {
        Person mother = Mockito.mock(Person.class);

        Person person1 = new Person("name", "male", mother);
        Person person2 = new Person("name", "male", mother);
        Assert.assertEquals(person1, person2);
    }

    @Test
    public void testPersonNonEquality() {
        Person mother = Mockito.mock(Person.class);

        Person person1 = new Person("name1", "male", mother);
        Person person2 = new Person("name2", "male", mother);
        Assert.assertNotEquals(person1, person2);
    }
}
