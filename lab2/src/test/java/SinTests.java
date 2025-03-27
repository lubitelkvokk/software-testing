import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.math.Sin;

public class SinTests {
    private static final double EPS3 = 1e-3;
    private static final double EPS5 = 1e-5;

    @Test
    public void emptySinConstructor() {
        Sin sin = new Sin();
        Assertions.assertNotNull(sin);
    }
    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, Math.PI - EPS5, Math.PI + 1e-5})
    void sinPiCheck(double value) {
        Assertions.assertEquals(Math.sin(value), Sin.sin(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI - EPS5, -Math.PI + 1e-5})
    void sinNegPiCheck(double value) {
        Assertions.assertEquals(Math.sin(value), Sin.sin(value), EPS3);
    }


    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Math.PI / 2 - EPS5, Math.PI / 2 + 1e-5})
    void sinHalfPiCheck(double value) {
        Assertions.assertEquals(Math.sin(value), Sin.sin(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, -Math.PI / 2 - EPS5, -Math.PI / 2 + 1e-5})
    void sinNegHalfPiCheck(double value) {
        Assertions.assertEquals(Math.sin(value), Sin.sin(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -EPS5, 1e-5})
    void sinZeroCheck(double value) {
        Assertions.assertEquals(Math.sin(value), Sin.sin(value), EPS3);
    }


    @Test
    void sinMaxIntValueCheck() {
        Assertions.assertEquals(Math.sin(200), Sin.sin(200), EPS3);
    }

    @Test
    void sinMinIntValueCheck() {
        Assertions.assertEquals(Math.sin(-200), Sin.sin(-200), EPS3);
    }



}
