import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.math.Tan;

public class TanTests {
    private static final double EPS3 = 1e-3;

    @Test
    public void emptyTanConstructor() {
        Tan tan = new Tan();
        Assertions.assertNotNull(tan);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, Math.PI - EPS3, Math.PI + EPS3})
    void tanPiCheck(double value) {
        Assertions.assertEquals(Math.tan(value), Tan.tan(value).doubleValue(), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI - EPS3, -Math.PI + EPS3})
    void tanNegPiCheck(double value) {
        Assertions.assertEquals(Math.tan(value), Tan.tan(value).doubleValue(), EPS3);
    }


    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 - EPS3, Math.PI / 2 + EPS3})
    void tanHalfPiCheck(double value) {
        Assertions.assertEquals(Math.tan(value), Tan.tan(value).doubleValue(), 1);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2})
    void tanNegHalfPiCheck(double value) {
        Assertions.assertThrows(ArithmeticException.class, () -> Tan.tan(value).doubleValue());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -EPS3, EPS3})
    void tanZeroCheck(double value) {
        Assertions.assertEquals(Math.tan(value), Tan.tan(value).doubleValue(), EPS3);
    }


    @Test
    void tanMaxIntValueCheck() {
        Assertions.assertEquals(Math.tan(200), Tan.tan(200).doubleValue(), EPS3);
    }

    @Test
    void tanMinIntValueCheck() {
        Assertions.assertEquals(Math.tan(-200), Tan.tan(-200).doubleValue(), EPS3);
    }

}
