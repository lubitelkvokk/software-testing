package se.ifmo;

public class LogN {
    public static double logN(double n, double x) {
        return Ln.ln(x) / Ln.ln(n);
    }
}
