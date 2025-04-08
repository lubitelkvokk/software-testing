package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;


public class Cos implements CsvWritableByStep {

    private final Sin sin;

    public Cos() {
        sin = new Sin();
    }

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double cos(double x) {
        double sinValue = sin.sin(x);
        x = x % (2 * Math.PI);
        if (x < 0) x += 2 * Math.PI;
        if (x >= Math.PI / 2 && x <= 3 * Math.PI / 2) return -Math.pow(Math.abs(1 - Math.pow(sinValue, 2)), 0.5);
        return Math.pow((1 - Math.pow(sinValue, 2)), 0.5);
    }

    @Override
    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            try {
                result = cos(startX + i * step);
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
