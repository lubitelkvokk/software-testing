import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.math.Csc;
import se.ifmo.math.Sin;

import java.util.stream.Stream;

import static java.lang.Double.NaN;

public class CscTest {
    private static final double EPS3 = 1e-3;


    private Csc csc;
    private Sin mockedSin;

    @BeforeEach
    public void setUp() {
        mockedSin = Mockito.mock(Sin.class);
        csc = new Csc(mockedSin);
    }


    @ParameterizedTest
    @MethodSource("testSinData")
    @DisplayName("Base csc realisation testing")
    public void testBaseCsc(double x) {
        double result;
        Csc baseCsc = new Csc();
        result = baseCsc.csc(x);
        Assertions.assertEquals(1 / Math.sin(x), result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testSinData")
    @DisplayName("Tesing on table of expected values")
    public void testCscPiPeriod(double x, double y, double expected) {
        double result;
        Mockito.when(mockedSin.sin(x)).thenReturn(y);
        result = csc.csc(x);
        Assertions.assertEquals(expected, result, EPS3);
        Mockito.verify(mockedSin).sin(x);
    }

    private static Stream<Arguments> testSinData() {
        return Stream.of(
                Arguments.of(0d, 0d, NaN),
                Arguments.of(Math.PI, 0d, NaN),
                Arguments.of(-Math.PI, 0d, NaN),
                Arguments.of(2 * Math.PI, 0d, NaN),
                Arguments.of(-2 * Math.PI, 0d, NaN),
                Arguments.of(Math.PI / 2, 1d, 1d),
                Arguments.of(-Math.PI / 2, -1d, -1d),
                Arguments.of(3 * Math.PI / 2, -1d, -1d),
                Arguments.of(Math.PI / 4, Math.sqrt(2) / 2, 2 / Math.sqrt(2)),
                Arguments.of(2 * Math.PI + Math.PI / 4, Math.sqrt(2) / 2, 2 / Math.sqrt(2)),
                Arguments.of(-Math.PI / 4, -Math.sqrt(2) / 2, -2 / Math.sqrt(2)),
                Arguments.of(-2 * Math.PI - Math.PI / 4, -Math.sqrt(2) / 2, -2 / Math.sqrt(2)),
                Arguments.of(3 * Math.PI / 4, Math.sqrt(2) / 2, 2 / Math.sqrt(2)),
                Arguments.of(-3 * Math.PI / 4, -Math.sqrt(2) / 2, -2 / Math.sqrt(2))
        ).filter(args -> Math.abs((Double) args.get()[1]) > EPS3);
    }
}
