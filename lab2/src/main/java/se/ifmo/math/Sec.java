package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;

public class Sec implements CsvWritableByStep {
    private final Cos cos;

    public Sec() {
        this.cos = new Cos();
    }

    public Sec(Cos cos) {
        this.cos = cos;
    }

    public double sec(double x) {
        return 1 / cos.cos(x);
    }


    @Override
    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            try {
                result = sec(startX + i * step);
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
