import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.math.Cos;
import se.ifmo.math.Cot;
import se.ifmo.math.Sin;

import java.util.stream.Stream;

import static java.lang.Double.NaN;

public class CotTests {
    private static final double EPS3 = 1e-3;


    private Cot cot;
    private Sin mockedSin;
    private Cos mockedCos;

    @BeforeEach
    public void setUp() {
        mockedSin = Mockito.mock(Sin.class);
        mockedCos = Mockito.mock(Cos.class);
        cot = new Cot(mockedSin, mockedCos);
    }


    @ParameterizedTest
    @MethodSource("testSinCosData")
    @DisplayName("Base cot realisation testing")
    public void testBaseCot(double x) {
        double result;
        Cot baseCot = new Cot();
        result = baseCot.cot(x).doubleValue();
        Assertions.assertEquals(1 / Math.tan(x), result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testSinCosData")
    @DisplayName("Testing on table of expected values")
    public void testCotPiPeriod(double x, double sinY, double cosY, double expected) {
        double result;
        Mockito.when(mockedSin.sin(x)).thenReturn(sinY);
        Mockito.when(mockedCos.cos(x)).thenReturn(cosY);

        result = cot.cot(x).doubleValue();
        Assertions.assertEquals(expected, result, EPS3);
        Mockito.verify(mockedSin).sin(x);
        Mockito.verify(mockedCos).cos(x);
    }

    private static Stream<Arguments> testSinCosData() {
        return Stream.of(
                Arguments.of(0d, 0d, 1d, NaN),
                Arguments.of(Math.PI, 0d, -1d, NaN),
                Arguments.of(-Math.PI, 0d, -1d, NaN),
                Arguments.of(2 * Math.PI, 0d, 1d, NaN),
                Arguments.of(-2 * Math.PI, 0d, 1d, NaN),
                Arguments.of(Math.PI / 2, 1d, 0d, 0d),
                Arguments.of(-Math.PI / 2, -1d, 0d, 0d),
                Arguments.of(3 * Math.PI / 2, -1d, 0d, 0d),
                Arguments.of(Math.PI / 4, Math.sqrt(2) / 2, Math.sqrt(2) / 2, 1d),
                Arguments.of(2 * Math.PI + Math.PI / 4, Math.sqrt(2) / 2, Math.sqrt(2) / 2, 1),
                Arguments.of(-Math.PI / 4, -Math.sqrt(2) / 2, Math.sqrt(2) / 2, -1),
                Arguments.of(-2 * Math.PI - Math.PI / 4, -Math.sqrt(2) / 2, Math.sqrt(2) / 2, -1),
                Arguments.of(3 * Math.PI / 4, Math.sqrt(2) / 2, -Math.sqrt(2) / 2, -1),
                Arguments.of(-3 * Math.PI / 4, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2, 1)

        ).filter(args -> Math.abs((Double) args.get()[1]) > EPS3);
    }

}
