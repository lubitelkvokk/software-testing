package se.ifmo;

import java.io.PrintWriter;

public class Sec {

    public static double sec(double x) {
        return 1 / Cos.cos(x);
    }
    public static void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            result = sec(startX + i * step);
            WriteCsv.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
