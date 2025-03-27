import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.math.Cos;

public class CosTests {
    private static final double EPS3 = 1e-3;
    private static final double EPS5 = 1e-5;

    @Test
    public void emptyCosConstructor() {
        Cos cos = new Cos();
        Assertions.assertNotNull(cos);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, Math.PI - EPS5, Math.PI + 1e-5})
    void cosPiCheck(double value) {
        Assertions.assertEquals(Math.cos(value), Cos.cos(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI - EPS5, -Math.PI + 1e-5})
    void cosNegPiCheck(double value) {
        Assertions.assertEquals(Math.cos(value), Cos.cos(value), EPS3);
    }


    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Math.PI / 2 - EPS5, Math.PI / 2 + 1e-5})
    void cosHalfPiCheck(double value) {
        Assertions.assertEquals(Math.cos(value), Cos.cos(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, -Math.PI / 2 - EPS5, -Math.PI / 2 + 1e-5})
    void cosNegHalfPiCheck(double value) {
        Assertions.assertEquals(Math.cos(value), Cos.cos(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -EPS5, 1e-5})
    void cosZeroCheck(double value) {
        Assertions.assertEquals(Math.cos(value), Cos.cos(value), EPS3);
    }


    @Test
    void cosMaxIntValueCheck() {
        Assertions.assertEquals(Math.cos(200), Cos.cos(200), EPS3);
    }

    @Test
    void cosMinIntValueCheck() {
        Assertions.assertEquals(Math.cos(-200), Cos.cos(-200), EPS3);
    }

}
