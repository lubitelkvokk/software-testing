import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import se.ifmo.Function;
import se.ifmo.math.*;

import java.math.BigDecimal;
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
    public void testTanPiPeriod(double x, TestFuncData data) {
        double result;
        Mockito.when(mockedSin.sin(x)).thenReturn(data.getSinValue());
        Mockito.when(mockedCos.cos(x)).thenReturn(data.getCosValue());
        Mockito.when(mockedCsc.csc(x)).thenReturn(data.getCscValue());
        Mockito.when(mockedSec.sec(x)).thenReturn(data.getSecValue());
        if (!isNaN(data.getTanValue())) {
            Mockito.when(mockedTan.tan(x)).thenReturn(BigDecimal.valueOf(data.getTanValue()));
        }
        if (!isNaN(data.getCotValue()))
            Mockito.when(mockedCot.cot(x)).thenReturn(BigDecimal.valueOf(data.getCotValue()));
        Mockito.when(mockedLog.logN(3d, x)).thenReturn(data.getLog3Value());
        Mockito.when(mockedLog.logN(5d, x)).thenReturn(data.getLog5Value());
        Mockito.when(mockedLog.logN(10d, x)).thenReturn(data.getLog10Value());

        result = func.calc(x, 5).doubleValue();
        Assertions.assertEquals(data.getExpected(), result, EPS3);
        if (x < 0) {
            Mockito.verify(mockedSin).sin(x);
            Mockito.verify(mockedCos).cos(x);
            Mockito.verify(mockedCsc).csc(x);
            Mockito.verify(mockedSec).sec(x);
            Mockito.verify(mockedTan).tan(x);
            Mockito.verify(mockedCot).cot(x);
        } else {
            Mockito.verify(mockedLog).logN(3, x);
            Mockito.verify(mockedLog).logN(5, x);
            Mockito.verify(mockedLog).logN(10, x);
        }
    }

    @Data
    @AllArgsConstructor
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
                Arguments.of(-0.99056, new TestFuncData(-0.83633, 0.54822, -1.19569, 1.82408, -1.52553, -0.65550, NaN, NaN, NaN, 0.84466)),
                Arguments.of(-0.99056 - twoPi, new TestFuncData(-0.83633, 0.54822, -1.19569, 1.82408, -1.52553, -0.65550, NaN, NaN, NaN, 0.84466)),

                // Вторая точка: x = -1.5708 (-π/2)
                Arguments.of(-1.5708, new TestFuncData(-1.00000, -0.00034, -1.00000, -2980.96292, 2980.96274, 0.00033, NaN, NaN, NaN, 1.0)),
                Arguments.of(-1.5708 - twoPi, new TestFuncData(-1.00000, -0.00034, -1.00000, -2980.96292, 2980.96274, 0.00033, NaN, NaN, NaN, 1.0)),

                // Третья точка: x = -2.22688
                Arguments.of(-2.22688, new TestFuncData(-0.79239, -0.61002, -1.26201, -1.63930, 1.29895, 0.76984, NaN, NaN, NaN, 0.62182)),
                Arguments.of(-2.22688 - twoPi, new TestFuncData(-0.79239, -0.61002, -1.26201, -1.63930, 1.29895, 0.76984, NaN, NaN, NaN, 0.62182)),

                // Четвёртая точка: x = -4.08369
                Arguments.of(-4.08369, new TestFuncData(0.80879, -0.58809, 1.23641, -1.70041, -1.37528, -0.72712, NaN, NaN, NaN, 0.23783)),
                Arguments.of(-4.08369 - twoPi, new TestFuncData(0.80879, -0.58809, 1.23641, -1.70041, -1.37528, -0.72712, NaN, NaN, NaN, 0.23783)),

                // Положительные x
                Arguments.of(0.22721, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, -1.34886, -0.92074, -0.64357, 0.0)),
                Arguments.of(0.32097, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, -1.03440, -0.70609, -0.49353, 0.4684)),
                Arguments.of(1.0, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, 0.0, 0.0, 0.0, 0.0)),
                Arguments.of(4.0, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, 1.26186, 0.86135, 0.60206, 1.36483))
        );
    }

}
