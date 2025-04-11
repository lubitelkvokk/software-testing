import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.math.*;

import java.util.stream.Stream;

public class LogNTests {
    private static final double EPS3 = 1e-3;

    private LogN logN;
    private Ln mockedLn;

    @BeforeEach
    public void setUp() {
        mockedLn = Mockito.mock(Ln.class);
        logN = new LogN(mockedLn);
    }


    @ParameterizedTest
    @MethodSource("testLnData")
    @DisplayName("Base LogN realisation testing")
    public void testBaseLogN(double x, double ignored_y, double base, double ignored_lnBase, double expected) {
        double result;
        LogN baseLogN = new LogN();
        result = baseLogN.logN(base, x);
        Assertions.assertEquals(expected, result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testLnData")
    @DisplayName("Testing on table of expected values")
    public void testLogN(double x, double lnX, double base, double lnBase, double expected) {
        double result;
        Mockito.when(mockedLn.ln(x)).thenReturn(lnX);
        Mockito.when(mockedLn.ln(base)).thenReturn(lnBase);

        result = logN.logN(base, x);
        Assertions.assertEquals(expected, result, EPS3);
        Mockito.verify(mockedLn).ln(x);
        Mockito.verify(mockedLn).ln(base);
    }

    private static Stream<Arguments> testLnData() {
        // {x, ln(x), base, ln(base), log_base(x)}
        return Stream.of(
                Arguments.of(Math.E, 1d, 3d, 1.099, 0.9102),
                Arguments.of(1d, 0d, 3d, 1.099, 0d),
                Arguments.of(100d, 4.605, 10d, 2.303, 2d)
        ).filter(args -> Math.abs((Double) args.get()[0]) > EPS3 && Math.abs((Double) args.get()[2]) > EPS3);
    }
}
