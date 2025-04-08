package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;

public class LogN {
    private final Ln ln;

    public LogN() {
        ln = new Ln();
    }

    public LogN(Ln ln) {
        this.ln = new Ln();
    }

    public double logN(double n, double x) {
        return ln.ln(x) / ln.ln(n);
    }

    public void writeCsvResult(double base, double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            try {
                result = logN(base, startX + i * step);
                CsvWorker.writeToFileDataLine(pw, new String[]{
                        String.valueOf(startX + i * step),
                        String.valueOf(result)});
            } catch (Exception e) {
                CsvWorker.writeToFileDataLine(pw, new String[]{
                        String.valueOf(startX + i * step),
                        "NaN"});
            }
        }
    }
}
