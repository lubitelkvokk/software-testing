package se.ifmo;

import java.math.BigDecimal;

public class Cot {
    public static BigDecimal cot(double x) {
        BigDecimal b = new BigDecimal(x);
        return BigDecimal.valueOf(Cos.cos(x)).divide(BigDecimal.valueOf(Sin.sin(x)), 5, 1);
    }
}
