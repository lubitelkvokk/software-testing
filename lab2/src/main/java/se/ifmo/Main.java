package se.ifmo;

import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("CosResult.txt");
        OutputStream outputStream = new FileOutputStream(file, true);
        PrintWriter pw = new PrintWriter(outputStream, true);
        Cos.writeCsvResult(Math.PI / 2, Math.PI / 8, 8, pw);
    }
}