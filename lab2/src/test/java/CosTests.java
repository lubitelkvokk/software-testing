import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.math.Cos;
import se.ifmo.math.Sin;

import java.util.stream.Stream;

public class CosTests {
    private static final double EPS3 = 1e-3;


    private Cos cos;
    private Sin mockedSin;

    @BeforeEach
    public void setUp() {
        mockedSin = Mockito.mock(Sin.class);
        cos = new Cos(mockedSin);
    }


    @ParameterizedTest
    @MethodSource("testSinData")
    @DisplayName("Base cos realisation testing")
    public void testBaseCos(double x, double ignored_y, double expected) {
        double result;
        Cos baseCos = new Cos();
        result = baseCos.cos(x);
        Assertions.assertEquals(expected, result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testSinData")
    @DisplayName("Tesing on table of expected values")
    public void testCosPeriod(double x, double y, double expected) {
        double result;
        Mockito.when(mockedSin.sin(x)).thenReturn(y);
        result = cos.cos(x);
        Mockito.verify(mockedSin).sin(x);
        Assertions.assertEquals(expected, result, EPS3);
    }

    private static Stream<Arguments> testSinData() {
        return Stream.of(
                Arguments.of(0d, 0d, 1d),
                Arguments.of(Math.PI, 0d, -1d),
                Arguments.of(-Math.PI, 0d, -1d),
                Arguments.of(2 * Math.PI, 0d, 1d),
                Arguments.of(-2 * Math.PI, 0d, 1d),
                Arguments.of(Math.PI / 2, 1d, 0d),
                Arguments.of(-Math.PI / 2, -1d, 0d),
                Arguments.of(3 * Math.PI / 2, -1d, 0d),
                Arguments.of(Math.PI / 4, Math.sqrt(2) / 2, Math.sqrt(2) / 2),
                Arguments.of(2 * Math.PI + Math.PI / 4, Math.sqrt(2) / 2, Math.sqrt(2) / 2),
                Arguments.of(-Math.PI / 4, -Math.sqrt(2) / 2, Math.sqrt(2) / 2),
                Arguments.of(-2 * Math.PI - Math.PI / 4, -Math.sqrt(2) / 2, Math.sqrt(2) / 2),
                Arguments.of(3 * Math.PI / 4, Math.sqrt(2) / 2, -Math.sqrt(2) / 2),
                Arguments.of(-3 * Math.PI / 4, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2)
        );
    }

}
