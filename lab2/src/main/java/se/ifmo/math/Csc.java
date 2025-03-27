package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;

public class Csc implements CsvWritableByStep {

    private Sin sin;

    public Csc() {
        this.sin = new Sin();
    }

    public Csc(Sin sin) {
        this.sin = sin;
    }

    public double csc(double x) {
        return 1 / sin.sin(x);
    }

    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            result = csc(startX + i * step);
            CsvWorker.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
