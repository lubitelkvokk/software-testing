package se.ifmo.part1;

import java.math.BigInteger;

public class Factorial {
    public static BigInteger getFactorial(int f) {
        if (f < 0) throw new ArithmeticException("Negative numbers don't have a factorial");
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= f; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
