package se.ifmo.math;

import se.ifmo.CsvWorker;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class Tan implements CsvWritableByStep{
    public static BigDecimal tan(double x) {
        return BigDecimal.valueOf(Sin.sin(x)).divide(BigDecimal.valueOf(Cos.cos(x)), 5, 1);
    }

    public static void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        BigDecimal result;
        for (int i = 0; i < count; i++) {
            result = tan(startX + i * step);
            CsvWorker.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
