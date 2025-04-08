package se.ifmo.math;

import java.io.PrintWriter;

public interface CsvWritableByStep {
    void writeCsvResult(double startX, double step, int count, PrintWriter pw);
}
