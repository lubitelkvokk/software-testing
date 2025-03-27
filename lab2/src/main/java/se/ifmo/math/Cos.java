package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;


public class Cos implements CsvWritableByStep{

    private Sin sin;

    public Cos(){
        sin = new Sin();
    }

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double cos(double x) {
        return sin.sin(x + Math.PI / 2);
    }

    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            result = cos(startX + i * step);
            CsvWorker.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
