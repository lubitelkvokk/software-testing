package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;
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
    public static void writeCsvResult(int startX, int step, int count, PrintWriter pw) {
        BigInteger result;
        for (int i = 0; i < count; i++) {
            result = getFactorial(startX + i * step);
            CsvWorker.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
