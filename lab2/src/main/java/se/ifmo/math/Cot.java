package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class Cot implements CsvWritableByStep {

    private Cos cos;
    private Sin sin;

    public Cot() {
        this.cos = new Cos();
        this.sin = new Sin();
    }

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public BigDecimal cot(double x) {
        return BigDecimal.valueOf(cos.cos(x)).divide(BigDecimal.valueOf(sin.sin(x)), 5, 1);
    }

    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        BigDecimal result;
        for (int i = 0; i < count; i++) {
            result = cot(startX + i * step);
            CsvWorker.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
