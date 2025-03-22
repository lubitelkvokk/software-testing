package se.ifmo;

import java.io.PrintWriter;


public class Cos {

    public static double cos(double x) {
        return Sin.sin(x + Math.PI / 2);
    }

    public static void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        double result;
        for (int i = 0; i < count; i++) {
            result = cos(startX + i * step);
            WriteCsv.writeToFileDataLine(pw, new String[]{
                    String.valueOf(startX + i * step),
                    String.valueOf(result)});
        }
    }
}
