package se.ifmo;

import java.math.BigDecimal;

public class Sin {
    public static double EPS = 1e-5;
    public static final int MAX_STEP = 1000;

    public static double calcNResult(int n, double x) {
        return Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / Factorial.getFactorial(2 * n + 1).doubleValue();
    }

    public static double sin(double x) {
        x = x % (2 * Math.PI);
        if (x > Math.PI) {
            x -= 2 * Math.PI;
        } else if (x < -Math.PI) {
            x += 2 * Math.PI;
        }
        double term;
        double sinResult = 0;
        int step = 0;
        while (step < MAX_STEP && Math.abs((term = calcNResult(step, x))) > EPS) {
            sinResult += term;
            step++;
        }
        if (step == MAX_STEP & Math.abs(calcNResult(step, x)) > EPS) {
            throw new ArithmeticException("Too much value for custom sin realization");
        }
        return sinResult;
    }
}
