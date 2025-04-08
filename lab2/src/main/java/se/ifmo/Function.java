package se.ifmo;

import se.ifmo.math.*;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Function implements CsvWritableByStep {

    private Cos cos;
    private Sin sin;
    private Csc csc;
    private Sec sec;
    private Tan tan;
    private Cot cot;
    private LogN logN;

    public Function() {
        this.cos = new Cos();
        this.sin = new Sin();
        this.csc = new Csc();
        this.sec = new Sec();
        this.tan = new Tan();
        this.cot = new Cot();
        this.logN = new LogN();
    }

    public Function(Sin sin, Cos cos, Csc csc, Sec sec, Tan tan, Cot cot, LogN logN) {
        this.cos = cos;
        this.sin = sin;
        this.csc = csc;
        this.sec = sec;
        this.tan = tan;
        this.cot = cot;
        this.logN = logN;
    }

    public BigDecimal calc(double x, int accuracy) {
        if (x <= 0) {
            BigDecimal cosResult = BigDecimal.valueOf(cos.cos(x));
            BigDecimal sinResult = BigDecimal.valueOf(sin.sin(x));
            BigDecimal cscResult = BigDecimal.valueOf(csc.csc(x));
            BigDecimal secResult = BigDecimal.valueOf(sec.sec(x));
            BigDecimal tanResult = tan.tan(x);
            BigDecimal cotResult = cot.cot(x);
            return cscResult.divide(secResult, accuracy, RoundingMode.HALF_UP).pow(2).multiply(tanResult).pow(12)
                    .add(cotResult.add(cscResult).multiply(cosResult.multiply(sinResult.pow(2)).add(sinResult)));
        } else {
            BigDecimal log3Result = BigDecimal.valueOf(logN.logN(3d, x));
            BigDecimal log5Result = BigDecimal.valueOf(logN.logN(5d, x));
            BigDecimal log10Result = BigDecimal.valueOf(logN.logN(10d, x));
            return (((log3Result.multiply(log10Result)).pow(3).add(log5Result)).subtract(log10Result.pow(3))).multiply(log3Result);
        }
    }

    public void writeCsvResult(double startX, double step, int count, PrintWriter pw) {
        BigDecimal result;
        for (int i = 0; i < count; i++) {
            try {
                result = calc(startX + i * step, 5);
                CsvWorker.writeToFileDataLine(pw, new String[]{
                        String.valueOf(startX + i * step),
                        String.valueOf(result)});
            } catch (Exception e) {
                CsvWorker.writeToFileDataLine(pw, new String[]{
                        String.valueOf(startX + i * step),
                        "NaN"});

            }
        }
    }
}
