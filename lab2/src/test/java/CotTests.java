import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.math.Cot;

public class CotTests {
    private static final double EPS3 = 1e-3;

    private Cot cot;
    @BeforeEach
    public void setUp() {
        this.cot = new Cot();
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI - EPS3, Math.PI + EPS3})
    void cotNearPiCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), cot.cot(value).doubleValue(), 1);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI - EPS3, -Math.PI + EPS3})
    void cotNearNegPiCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), cot.cot(value).doubleValue(), 1);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 - EPS3, Math.PI / 2 + EPS3})
    void cotHalfPiCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), cot.cot(value).doubleValue(), 1);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0})
    void cotZeroCheck(double value) {
        Assertions.assertThrows(ArithmeticException.class, () -> cot.cot(value).doubleValue());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-EPS3, EPS3})
    void cotNearZeroCheck(double value) {
        Assertions.assertEquals(1 / Math.tan(value), cot.cot(value).doubleValue(), 1);
    }

    @Test
    void cotMaxIntValueCheck() {
        Assertions.assertEquals(1 / Math.tan(200), cot.cot(200).doubleValue(), EPS3);
    }

    @Test
    void cotMinIntValueCheck() {
        Assertions.assertEquals(1 / Math.tan(-200), cot.cot(-200).doubleValue(), EPS3);
    }

}
