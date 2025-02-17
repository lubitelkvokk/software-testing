package se.ifmo.part1;

public class Cos {
    public static double EPS = 1e-5;

    private static double calcNResult(int n, double x) {
        return Math.pow(-1, n) * Math.pow(x, 2 * n) / Factorial.getFactorial(2 * n).doubleValue();
    }

    public static double cos(double x) {
        double term;
        double cosResult = 0;
        int step = 0;
        while (Math.abs((term = calcNResult(step, x))) > EPS) {
            cosResult += term;
            step++;
        }
        return cosResult;
    }
}
