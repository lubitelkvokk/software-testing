package se.ifmo;

import java.io.PrintWriter;

public class LogN {
    public static double logN(double n, double x) {
        return Ln.ln(x) / Ln.ln(n);
    }

    public static void writeCsvResult(double base, double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            result = logN(base, startX + i * step);
            WriteCsv.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
