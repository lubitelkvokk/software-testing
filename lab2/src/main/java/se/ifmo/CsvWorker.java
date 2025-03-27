package se.ifmo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvWorker {
    private static final String COMMA_DELIMITER = ",";

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
                .map(CsvWorker::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public static void writeToFileDataLine(PrintWriter pw, String[] dataLine) {
        pw.println(convertToCSV(dataLine));
    }

    public static void writeToFileDataLines(PrintWriter pw, List<String[]> dataLines) {
        dataLines.stream()
                .map(CsvWorker::convertToCSV)
                .forEach(pw::println);
    }

    public static List<List<String>> readCsvFile(String fileName) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
}
