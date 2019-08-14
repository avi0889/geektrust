import breakerofchains.BallotTest;
import breakerofchains.BreakerOfChainsElectionTest;
import breakerofchains.BreakerOfChainsInputProcessorTest;
import goldencrown.GoldenCrownElectionTest;
import goldencrown.GoldenCrownInputProcessorTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GoldenCrownElectionTest.class, GoldenCrownInputProcessorTest.class,
                BallotTest.class, BreakerOfChainsElectionTest.class, BreakerOfChainsInputProcessorTest.class,
                KingdomTest.class, MessageTest.class, KingdomFactoryImplTest.class);
        for(Failure failure : result.getFailures())
            System.out.println(failure.toString());
        System.out.println(result.wasSuccessful());
    }
}
