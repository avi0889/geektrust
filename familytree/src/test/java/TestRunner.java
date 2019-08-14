import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.initialize.Initializer;
import com.geektrust.familytree.repository.DescendantRepository;
import com.geektrust.familytree.repository.PartnerRepository;
import com.geektrust.familytree.repository.RelativesRepository;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.HashMap;
import java.util.Map;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(InitializerTest.class, PersonTest.class, InputParserTest.class,
                RelationTest.class);
        for(Failure failure : result.getFailures())
            System.out.println(failure.toString());
        System.out.println(result.wasSuccessful());
    }
}
