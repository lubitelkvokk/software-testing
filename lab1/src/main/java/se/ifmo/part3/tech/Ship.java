package se.ifmo.part3.tech;

import lombok.Data;
import se.ifmo.part3.tech.buttons.Button;

import java.util.ArrayList;

@Data
public class Ship {
    private State state = State.WORKING;
    private ControlPanel controlPanel;
    private Locator locator;
    private String name;

    public Ship(ArrayList<Button> buttons) {
        locator = new Locator();
        controlPanel = new ControlPanel(this, buttons);
    }

    public void moveForward(double x) {
        locator.appendX(x);
    }

    public void moveBackward(double x) {
        locator.decreaseX(x);
    }

    public void moveLeft(double y) {
        locator.decreaseY(y);
    }

    public void moveRight(double y) {
        locator.appendY(y);
    }
}
