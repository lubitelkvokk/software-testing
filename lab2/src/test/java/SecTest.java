import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.math.Sec;
import se.ifmo.math.Cos;

import java.util.stream.Stream;

import static java.lang.Double.NaN;

public class SecTest {
    private static final double EPS3 = 1e-3;

    private Sec sec;
    private Cos mockedCos;

    @BeforeEach
    public void setUp() {
        mockedCos = Mockito.mock(Cos.class);
        sec = new Sec(mockedCos);
    }

    @ParameterizedTest
    @MethodSource("testCosData")
    @DisplayName("Base sec realisation testing")
    public void testBaseSec(double x) {
        double result;
        Sec baseSec = new Sec();
        result = baseSec.sec(x);
        Assertions.assertEquals(1 / Math.cos(x), result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testCosData")
    @DisplayName("Tecosg on table of expected values")
    public void testSecPeriod(double x, double y, double expected) {
        double result;
        Mockito.when(mockedCos.cos(x)).thenReturn(y);
        result = sec.sec(x);
        Assertions.assertEquals(expected, result, EPS3);
        Mockito.verify(mockedCos).cos(x);
    }

    private static Stream<Arguments> testCosData() {
        return Stream.of(
                Arguments.of(0d, 1d, 1d),
                Arguments.of(Math.PI, 0d, NaN),
                Arguments.of(-Math.PI, 0d, NaN),
                Arguments.of(2 * Math.PI, 0d, NaN),
                Arguments.of(-2 * Math.PI, 0d, NaN),
                Arguments.of(Math.PI / 2, 0d, NaN),
                Arguments.of(-Math.PI / 2, 0d, NaN),
                Arguments.of(3 * Math.PI / 2, 0d, -1d),
                Arguments.of(Math.PI / 4, Math.sqrt(2) / 2, 2 / Math.sqrt(2)),
                Arguments.of(2 * Math.PI + Math.PI / 4, Math.sqrt(2) / 2, 2 / Math.sqrt(2)),
                Arguments.of(-Math.PI / 4, -Math.sqrt(2) / 2, -2 / Math.sqrt(2)),
                Arguments.of(-2 * Math.PI - Math.PI / 4, -Math.sqrt(2) / 2, -2 / Math.sqrt(2)),
                Arguments.of(3 * Math.PI / 4, Math.sqrt(2) / 2, 2 / Math.sqrt(2)),
                Arguments.of(-3 * Math.PI / 4, -Math.sqrt(2) / 2, -2 / Math.sqrt(2))
        ).filter(args -> Math.abs((Double) args.get()[1]) > EPS3);
    }
}
