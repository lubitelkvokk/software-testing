package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;

public class Ln implements CsvWritableByStep{
    public static double EPS = 1e-6;
    public static final int MAX_STEP = 10000;

    private static double ln1PlusX(double y) {
        if (Math.abs(y) >= 1) {
            throw new IllegalArgumentException("|x| must be less than 1 for this series to converge.");
        }
        double result = 0;
        int n = 1;
        double term;
        while (n < MAX_STEP && Math.abs((term = Math.pow(-1, n + 1) * Math.pow(y, n) / n)) > EPS) {
            result += term;
            n++;
        }
        return result;
    }

    public static double ln(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be positive.");
        }

        int k = 0;
        while (x > 2) {
            x /= Math.E;
            k++;
        }
        return ln1PlusX(x - 1) + k;
    }

    public static void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            result = ln(startX + i * step);
            CsvWorker.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
