package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class Tan implements CsvWritableByStep {
    private final Sin sin;
    private final Cos cos;

    public Tan() {
        this.sin = new Sin();
        this.cos = new Cos();
    }

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public BigDecimal tan(double x) {
        return BigDecimal.valueOf(sin.sin(x)).divide(BigDecimal.valueOf(cos.cos(x)), 5, 1);
    }

    @Override
    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        BigDecimal result;
        for (int i = 0; i < count; i++) {
            try {
                result = tan(startX + i * step);
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
