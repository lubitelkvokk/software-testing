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
    @MethodSource({"testMonotonicData", "testBoundaryData", "testPeriodicData"})
    @DisplayName("Base func realisation testing")
    public void testBaseFunc(double x, TestFuncData data) {
        double result;
        Function function = new Function();
        result = function.calc(x, 5).doubleValue();
        Assertions.assertEquals(data.getExpected(), result, EPS3);
    }

    @ParameterizedTest
    @MethodSource("testMonotonicData")
    @DisplayName("Testing on monotonic section")
    public void testMonotonicSection(double x, TestFuncData data){
        testTargetFunc(x, data);
    }

    @ParameterizedTest
    @MethodSource("testBoundaryData")
    @DisplayName("Testing on boundary values")
    public void testBoundaryData(double x, TestFuncData data){
        testTargetFunc(x, data);
    }

    @ParameterizedTest
    @MethodSource("testPeriodicData")
    @DisplayName("Testing on periodic")
    public void testPeriodicData(double x, TestFuncData data){
        testTargetFunc(x, data);
    }

    public void testTargetFunc(double x, TestFuncData data) {
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
    private static Stream<Arguments> testMonotonicData() {
        return Stream.of(
                Arguments.of(-0.78, new TestFuncData(-0.70323,0.71091, -1.42191, 1.40664, -0.98926, -1.01085, NaN, NaN, NaN, 1.99383)),
                Arguments.of(-1.3, new TestFuncData(-0.96356,0.26749, -1.03782, 3.73835, -3.60212, -0.27761, NaN, NaN, NaN, 0.9408)),
                Arguments.of(-2, new TestFuncData(-0.90929, -0.41614, -1.09975, -2.40299, 2.18504, 0.45765, NaN, NaN, NaN, 0.80487)),
                Arguments.of(-2.37, new TestFuncData(-0.69728, -0.7168, -1.43415, -1.39508, 0.97276, 1.02799, NaN, NaN, NaN, 1.81763)),
                Arguments.of(-5, new TestFuncData(0.95892, 0.28366, 1.04283, 3.52533, 3.38053, 0.29581, NaN, NaN, NaN, 1.63283)),
                Arguments.of(-5.542, new TestFuncData(0.67516,0.73766, 1.48112,1.35562, 0.91526, 1.09257, NaN, NaN, NaN, 5.49669)),


                Arguments.of(0.22721, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, -1.34886, -0.92074, -0.64357, 0.0)),
                Arguments.of(1.0, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, 0.0, 0.0, 0.0, 0.0)),
                Arguments.of(4.0, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, 1.26186, 0.86135, 0.60206, 1.36483))

        );
    }

    private static Stream<Arguments> testBoundaryData() {
        return Stream.of(
                Arguments.of(-0.99056, new TestFuncData(-0.83633, 0.54822, -1.19569, 1.82408, -1.52553, -0.65550, NaN, NaN, NaN, 0.84466)),

                Arguments.of(-1.5708, new TestFuncData(-1.00000, -0.00034, -1.00000, -2980.96292, 2980.96274, 0.00033, NaN, NaN, NaN, 1.0)),

                Arguments.of(-2.22688, new TestFuncData(-0.79239, -0.61002, -1.26201, -1.63930, 1.29895, 0.76984, NaN, NaN, NaN, 0.62182)),

                Arguments.of(-4.08369, new TestFuncData(0.80879, -0.58809, 1.23641, -1.70041, -1.37528, -0.72712, NaN, NaN, NaN, 0.23783)),

                Arguments.of(0.32097, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, -1.03440, -0.70609, -0.49353, 0.4684))
        );
    }

    private static Stream<Arguments> testPeriodicData() {
        double twoPi = 2 * Math.PI;

        return Stream.of(
                Arguments.of(-0.78 - twoPi, new TestFuncData(-0.70323,0.71091, -1.42191, 1.40664, -0.98926, -1.01085, NaN, NaN, NaN, 1.99383)),
                Arguments.of(-1.3 - twoPi, new TestFuncData(-0.96356,0.26749, -1.03782, 3.73835, -3.60212, -0.27761, NaN, NaN, NaN, 0.9408)),
                Arguments.of(-2 - twoPi, new TestFuncData(-0.90929, -0.41614, -1.09975, -2.40299, 2.18504, 0.45765, NaN, NaN, NaN, 0.80487)),
                Arguments.of(-2.37 - twoPi, new TestFuncData(-0.69728, -0.7168, -1.43415, -1.39508, 0.97276, 1.02799, NaN, NaN, NaN, 1.81763)),
                Arguments.of(-5 - twoPi, new TestFuncData(0.95892, 0.28366, 1.04283, 3.52533, 3.38053, 0.29581, NaN, NaN, NaN, 1.63283)),
                Arguments.of(-5.542 - twoPi, new TestFuncData(0.67516,0.73766, 1.48112,1.35562, 0.91526, 1.09257, NaN, NaN, NaN, 5.49669)),

                Arguments.of(-0.99056 - twoPi, new TestFuncData(-0.83633, 0.54822, -1.19569, 1.82408, -1.52553, -0.65550, NaN, NaN, NaN, 0.84466)),

                Arguments.of(-1.5708 - twoPi, new TestFuncData(-1.00000, -0.00034, -1.00000, -2980.96292, 2980.96274, 0.00033, NaN, NaN, NaN, 1.0)),

                Arguments.of(-2.22688 - twoPi, new TestFuncData(-0.79239, -0.61002, -1.26201, -1.63930, 1.29895, 0.76984, NaN, NaN, NaN, 0.62182)),

                Arguments.of(-4.08369 - twoPi, new TestFuncData(0.80879, -0.58809, 1.23641, -1.70041, -1.37528, -0.72712, NaN, NaN, NaN, 0.23783)),

                Arguments.of(0.32097, new TestFuncData(NaN, NaN, NaN, NaN, NaN, NaN, -1.03440, -0.70609, -0.49353, 0.4684))

        );
    }

}
