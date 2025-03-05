package se.ifmo.part1;

public class Cos {
    public static double EPS = 1e-5;
    public static final int MAX_STEP = 50;

    public static double calcNResult(int n, double x) {
        return Math.pow(-1, n) * Math.pow(x, 2 * n) / Factorial.getFactorial(2 * n).doubleValue();

    }

    public static double cos(double x) {
        double term;
        double cosResult = 0;
        int step = 0;
        while (step < MAX_STEP && Math.abs((term = calcNResult(step, x))) > EPS) {
            cosResult += term;
            step++;
        }
        if (step == MAX_STEP & Math.abs(calcNResult(step, x)) > EPS) {
            throw new ArithmeticException("Too much value for custom cos realization");
        }
        return cosResult;
    }
}
