package se.ifmo;

import java.math.BigDecimal;

public class Tan {
    public static BigDecimal tan(double x) {
        return BigDecimal.valueOf(Sin.sin(x)).divide(BigDecimal.valueOf(Cos.cos(x)), 5, 1);
    }
}
