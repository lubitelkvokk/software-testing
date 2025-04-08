import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import se.ifmo.math.Cos;
import se.ifmo.math.Cot;
import se.ifmo.math.Sin;
import se.ifmo.math.Tan;

import java.util.stream.Stream;

public class TargetFunctionTest {
    private static final double EPS3 = 1e-3;

    private Tan cot;
    private Sin mockedSin;
    private Cos mockedCos;
    private Tan mockedTan;
    private Cot mockedCot;
    private Log mockedLog;

    @BeforeEach
    public void setUp() {
        mockedSin = Mockito.mock(Sin.class);
        mockedCos = Mockito.mock(Cos.class);
        cot = new Tan(mockedSin, mockedCos);
    }


    @ParameterizedTest
    @MethodSource("testSinCosData")
    @DisplayName("Base cot realisation testing")
    public void testBaseTan(double x) {
        double result;
        Tan baseTan = new Tan();
        result = baseTan.tan(x).doubleValue();
        Assertions.assertEquals(Math.tan(x), result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testSinCosData")
    @DisplayName("Testing on table of expected values")
    public void testTanPiPeriod(double x, double sinY, double cosY) {
        double result;
        Mockito.when(mockedSin.sin(x)).thenReturn(sinY);
        Mockito.when(mockedCos.cos(x)).thenReturn(cosY);

        result = cot.tan(x).doubleValue();
        Assertions.assertEquals(Math.tan(x), result, EPS3);
        Mockito.verify(mockedSin).sin(x);
        Mockito.verify(mockedCos).cos(x);
    }

    private static Stream<Arguments> testSinCosData() {
        // {x, sin(x), cos(x), csc(x), sec(x), tan(x), cot(x), log_3(x), log_5(x), log_10(x), expected(x)}
        return Stream.of(
                Arguments.of(-0.99056, -0.83633, 0.54822, -1.19569, ),
        ).filter(args -> Math.abs((Double) args.get()[2]) > EPS3);
    }}
