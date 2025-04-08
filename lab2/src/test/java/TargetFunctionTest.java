import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.CsvWorker;
import se.ifmo.Function;
import se.ifmo.math.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;

public class TargetFunctionTest {
    private static final double EPS3 = 1e-3;

    private Function func;

    private Sin mockedSin;
    private Cos mockedCos;
    private Csc mockedCsc;
    private Sec mockedSec;
    private Tan mockedTan;
    private Cot mockedCot;
    private LogN mockedLog;

    @BeforeEach
    public void setUp() {
        mockedSin = Mockito.mock(Sin.class);
        mockedCos = Mockito.mock(Cos.class);
        mockedTan = Mockito.mock(Tan.class);
        mockedCot = Mockito.mock(Cot.class);
        mockedLog = Mockito.mock(LogN.class);
        mockedCsc = Mockito.mock(Csc.class);
        mockedSec = Mockito.mock(Sec.class);
        func = new Function(mockedSin, mockedCos, mockedCsc, mockedSec, mockedTan, mockedCot, mockedLog);
    }

    @ParameterizedTest
    @MethodSource("testFuncData")
    @DisplayName("Testing on table of expected values")
    public void testTanPiPeriod(double x, double sinY, double cosY, double cscY, double secY, double tanY, double cotY,
                                double log3Y, double log5Y, double log10Y, double expected) {
        double result;
        Mockito.when(mockedSin.sin(x)).thenReturn(sinY);
        Mockito.when(mockedCos.cos(x)).thenReturn(cosY);
        Mockito.when(mockedCsc.csc(x)).thenReturn(cscY);
        Mockito.when(mockedSec.sec(x)).thenReturn(secY);
        if (!isNaN(tanY))
            Mockito.when(mockedTan.tan(x)).thenReturn(BigDecimal.valueOf(tanY));
        if (!isNaN(cotY))
            Mockito.when(mockedCot.cot(x)).thenReturn(BigDecimal.valueOf(cotY));
        Mockito.when(mockedLog.logN(3d, x)).thenReturn(log3Y);
        Mockito.when(mockedLog.logN(5d, x)).thenReturn(log5Y);
        Mockito.when(mockedLog.logN(10d, x)).thenReturn(log10Y);

        result = func.calc(x, 5).doubleValue();
        Assertions.assertEquals(expected, result, EPS3);
        if (x < 0){
            Mockito.verify(mockedSin).sin(x);
            Mockito.verify(mockedCos).cos(x);
            Mockito.verify(mockedCsc).csc(x);
            Mockito.verify(mockedSec).sec(x);
            Mockito.verify(mockedTan).tan(x);
            Mockito.verify(mockedCot).cot(x);
        }
        else {
            Mockito.verify(mockedLog).logN(3, x);
            Mockito.verify(mockedLog).logN(5, x);
            Mockito.verify(mockedLog).logN(10, x);
        }
    }

    @Data
    private static class TestFuncData {
        private Double sinValue;
        private Double cosValue;
        private Double cscValue;
        private Double secValue;
        private Double tanValue;
        private Double cotValue;
        private Double log3Value;
        private Double log5Value;
        private Double log10Value;
        private Double expected;
    }

    private static Stream<Arguments> testFuncData() {
        double twoPi = 2 * Math.PI; // ~6.28318530718

        return Stream.of(
                // {x, sin(x), cos(x), csc(x), sec(x), tan(x), cot(x), log3(x), log5(x), log10(x), expected}

                // Первая точка: x = -0.99056
                Arguments.of(-0.99056, -0.83633, 0.54822, -1.19569, 1.82408, -1.52553, -0.65550, Double.NaN, Double.NaN, Double.NaN, 0.84466),
                Arguments.of(-0.99056 - twoPi, -0.83633, 0.54822, -1.19569, 1.82408, -1.52553, -0.65550, Double.NaN, Double.NaN, Double.NaN, 0.84466),

                // Вторая точка: x = -1.5708 (-π/2)
                Arguments.of(-1.5708, -1.00000, -0.00034, -1.00000, -2980.96292, 2980.96274, 0.00033, Double.NaN, Double.NaN, Double.NaN, 1.0),
                Arguments.of(-1.5708 - twoPi, -1.00000, -0.00034, -1.00000, -2980.96292, 2980.96274, 0.00033, Double.NaN, Double.NaN, Double.NaN, 1.0),

                // Третья точка: x = -2.22688
                Arguments.of(-2.22688, -0.79239, -0.61002, -1.26201, -1.63930, 1.29895, 0.76984, Double.NaN, Double.NaN, Double.NaN, 0.62182),
                Arguments.of(-2.22688 - twoPi, -0.79239, -0.61002, -1.26201, -1.63930, 1.29895, 0.76984, Double.NaN, Double.NaN, Double.NaN, 0.62182),

                // Четвёртая точка: x = -4.08369
                Arguments.of(-4.08369, 0.80879, -0.58809, 1.23641, -1.70041, -1.37528, -0.72712, Double.NaN, Double.NaN, Double.NaN, 0.23783),
                Arguments.of(-4.08369 - twoPi, 0.80879, -0.58809, 1.23641, -1.70041, -1.37528, -0.72712, Double.NaN, Double.NaN, Double.NaN, 0.23783),

                // Положительные x
                Arguments.of(0.22721, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, -1.34886, -0.92074, -0.64357, 0),
                Arguments.of(0.32097, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, -1.03440, -0.70609, -0.49353, 0.4684),
                Arguments.of(1.0, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, 0.0, 0.0, 0.0, 0),
                Arguments.of(4.0, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN, 1.26186, 0.86135, 0.60206, 1.36483)        );
    }
}
