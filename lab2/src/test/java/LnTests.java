import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.math.Ln;

public class LnTests {
    private static final double EPS3 = 1e-3;
    private static final double EPS5 = 1e-5;

    @Test
    public void emptyLnConstructor() {
        Ln ln = new Ln();
        Assertions.assertNotNull(ln);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.E, Math.E - EPS5, Math.E + 1e-5})
    void lnECheck(double value) {
        Assertions.assertEquals(Math.log(value), Ln.ln(value), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -EPS5})
    void lnZeroCheck(double value) {
        Assertions.assertThrows(IllegalArgumentException.class,() -> Ln.ln(value));
    }

    @Test
    void lnBigIntValueCheck() {
        Assertions.assertEquals(Math.log(200), Ln.ln(200), EPS3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {EPS3})
    void lnSmallIntValueCheck(double eps) {
        Assertions.assertEquals(Math.log(eps), Ln.ln(eps), EPS3);
    }

}
