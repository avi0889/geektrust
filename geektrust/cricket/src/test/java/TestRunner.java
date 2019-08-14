import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BallTest.class, OverTest.class, PlayerStatTest.class, PlayerTest.class,
                ScoreBoardTest.class, RandomizerImplTest.class, RandomOutcomeTest.class);
        for(Failure failure : result.getFailures())
            System.out.println(failure.toString());
        System.out.println(result.wasSuccessful());
    }
}
