package se.ifmo;

import lombok.extern.java.Log;
import se.ifmo.math.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    private static Double EPS = 1e-3;

    public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Path path = Paths.get("src/main/resources");
        Class[] classes = new Class[]{Cos.class,
                Sin.class,
                Csc.class,
                Sec.class,
                Cot.class,
                Tan.class,
        };
        Double[] xArray = new Double[]{-5.5, -5d, -4.08369, -3.85, -Math.PI, -2.45, -2.22687,
                -2d, -1.5708, -1.28, -0.99056, -0.7, 0d, 0.17323, 0.2802, 0.5, 6d};
        Double[] logNBaseArray = new Double[]{3d, 5d, 10d};
        LogN logN = new LogN();
        for (double logNBase : logNBaseArray) {
            File resultFile = new File(path + "/Log" + logNBase + ".csv");
            resultFile.delete();
            OutputStream outputStream = new FileOutputStream(resultFile, true);
            PrintWriter pw = new PrintWriter(outputStream, true);
            for (Double x : xArray) {
                if (x > 0) logN.writeCsvResult(logNBase, x - EPS, EPS, 3, pw);
            }
        }

        for (Class classObj : classes) {
            File resultFile = new File(path + "/" + classObj.getSimpleName() + ".csv");
            resultFile.delete();
            OutputStream outputStream = new FileOutputStream(resultFile, true);
            PrintWriter pw = new PrintWriter(outputStream, true);
            CsvWritableByStep csvWritableByStep = (CsvWritableByStep) classObj.getDeclaredConstructor().newInstance();

            for (Double x : xArray) {
                if (x <= 0) {
                    csvWritableByStep.writeCsvResult(x - EPS, EPS, 3, pw);
                }
            }
        }
    }
}