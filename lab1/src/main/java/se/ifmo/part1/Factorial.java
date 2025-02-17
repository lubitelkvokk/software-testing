package se.ifmo.part1;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Factorial {
    public static BigInteger getFactorial(int f) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= f; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
