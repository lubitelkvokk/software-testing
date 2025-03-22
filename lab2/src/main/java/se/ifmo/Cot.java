package se.ifmo;

import java.io.PrintWriter;
import java.math.BigDecimal;

public class Cot {
    public static BigDecimal cot(double x) {
        BigDecimal b = new BigDecimal(x);
        return BigDecimal.valueOf(Cos.cos(x)).divide(BigDecimal.valueOf(Sin.sin(x)), 5, 1);
    }
    public static void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        BigDecimal result;
        for (int i = 0; i < count; i++) {
            result = cot(startX + i * step);
            WriteCsv.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
