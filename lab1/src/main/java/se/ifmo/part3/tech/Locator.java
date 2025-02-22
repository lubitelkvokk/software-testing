package se.ifmo.part3.tech;

import lombok.Data;

@Data
public class Locator {
    private double x;
    private double y;
    private double z;

    public Locator() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void appendX(double d) {
        x = x + d;
    }

    public void appendY(double d) {
        y = y + d;
    }

    public void decreaseX(double d) {
        x = x - d;
    }

    public void decreaseY(double d) {
        y = y - d;
    }
}
