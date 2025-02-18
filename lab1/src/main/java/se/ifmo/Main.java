package se.ifmo;

import se.ifmo.part3.Actor;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.buttons.Button;
import se.ifmo.part3.tech.buttons.ButtonType;
import se.ifmo.part3.tech.buttons.ExplosionButton;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        ExplosionButton explosionButton = new ExplosionButton();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(explosionButton);
        buttons.add(new Button());
        Ship ship = new Ship(buttons);

        ControlPanel cp = ship.getControlPanel();
        Actor actor = new Actor();
        actor.use(cp, ButtonType.UNKNOWN_BUTTON);

    }
}