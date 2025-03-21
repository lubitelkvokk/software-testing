import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import se.ifmo.Factorial;

import java.math.BigInteger;

public class FactorialTests {

    @Test
    public void emptyFactorialConstructor() {
        Factorial factorial = new Factorial();
        Assertions.assertNotNull(factorial);
    }


    @ParameterizedTest
    @CsvSource({"0, 1", "1, 1", "5, 120", "101, 9425947759838359420851623124482936749562312794702543768327889353416977599316221476503087861591808346911623490003549599583369706302603264000000000000000000000000"})
    void factorialCheck(int value, BigInteger expected) {
        Assertions.assertEquals(expected, Factorial.getFactorial(value));
    }

    @Test
    void factorialNegativeCheck() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Factorial.getFactorial(-1);
        });
    }


}
