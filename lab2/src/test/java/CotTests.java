import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.Cot;

public class CotTests {
    private static final double EPS3 = 1e-3;

    @Test
    public void emptyCotConstructor() {
        Cot cot = new Cot();
        Assertions.assertNotNull(cot);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI - EPS3, Math.PI + EPS3})
    void cotNearPiCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), Cot.cot(value).doubleValue(), 1);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI - EPS3, -Math.PI + EPS3})
    void cotNearNegPiCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), Cot.cot(value).doubleValue(), 1);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 - EPS3, Math.PI / 2 + EPS3})
    void cotHalfPiCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), Cot.cot(value).doubleValue(), 1);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0})
    void cotZeroCheck(double value) {
        Assertions.assertThrows(ArithmeticException.class, () -> Cot.cot(value).doubleValue());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-EPS3, EPS3})
    void cotNearZeroCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), Cot.cot(value).doubleValue(), 1);
    }

    @Test
    void cotMaxIntValueCheck() {
        Assertions.assertEquals(1 / Math.tan(200), Cot.cot(200).doubleValue(), EPS3);
    }

    @Test
    void cotMinIntValueCheck() {
        Assertions.assertEquals(1 / Math.tan(-200), Cot.cot(-200).doubleValue(), EPS3);
    }

}
