package se.ifmo;

import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteCsv {
    public static String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("'")) {
            escapedData = escapedData.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }

    public static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(WriteCsv::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public static void writeToFileDataLine(PrintWriter pw, String[] dataLine) {
        pw.println(convertToCSV(dataLine));
    }

    public static void writeToFileDataLines(PrintWriter pw, List<String[]> dataLines) {
        dataLines.stream()
                .map(WriteCsv::convertToCSV)
                .forEach(pw::println);
    }
}
