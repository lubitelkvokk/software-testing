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

    public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Function function = new Function();
//        Path path = Paths.get("src/main/resources");
//        Class[] classes = new Class[]{Cos.class,
//                Sin.class,
//                Csc.class,
//                Sec.class,
//                Cot.class,
//                Tan.class,
//        };
//        Double[] xArray = new Double[]{-5.5, -5d, -4.08369, -3.85, -Math.PI, -2.45, -2.22687,
//                -2d, -1.5708, -1.28, -0.99056, -0.7, 0d, 0.17323, 0.2802, 0.5, 6d};
//        Double[] logNBaseArray = new Double[]{3d, 5d, 10d};
//        for (double logNBase : logNBaseArray) {
//            File resultFile = new File(path + "/Log" + logNBase + ".csv");
//            resultFile.delete();
//            OutputStream outputStream = new FileOutputStream(resultFile, true);
//            PrintWriter pw = new PrintWriter(outputStream, true);
//            for (Double x : xArray) {
//                if (x > 0) LogN.writeCsvResult(logNBase, x - EPS, EPS, 3, pw);
//            }
//        }
//
//        for (Class classObj : classes) {
//            File resultFile = new File(path + "/" + classObj.getSimpleName() + ".csv");
//            resultFile.delete();
//            OutputStream outputStream = new FileOutputStream(resultFile, true);
//            PrintWriter pw = new PrintWriter(outputStream, true);
//            Method method = classObj.getMethod("writeCsvResult",
//                    double.class, double.class, int.class, PrintWriter.class);
//            for (Double x : xArray) {
//                if (x <= 0) {
//                    try {
//                        method.invoke(null, x - EPS, EPS, 3, pw);
//                    } catch (InvocationTargetException e) {
//                    }
//                }
//            }
//        }
    }
}