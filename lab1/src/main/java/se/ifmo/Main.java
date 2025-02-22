package se.ifmo;

import se.ifmo.part3.human.*;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.buttons.Button;
import se.ifmo.part3.tech.buttons.ButtonType;
import se.ifmo.part3.tech.buttons.ExplosionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        ExplosionButton explosionButton = new ExplosionButton();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(explosionButton);
        buttons.add(new Button());
        Ship ship = new Ship(buttons);

        ControlPanel cp = ship.getControlPanel();
//        Actor actor = new Actor();
//        actor.use(cp, ButtonType.UNKNOWN_BUTTON);
        Zaphod zaphod = new Zaphod();
        zaphod.setQualities(new ArrayList<>(Arrays.asList(Qualities.ARROGANCE,
                Qualities.BRAVADO,
                Qualities.IMPETUOSITY,
                Qualities.RESTLESSNESS)));
        zaphod.interactWith(cp, Task.DRUMMING_SOMETHING);
        zaphod.interactWith(cp, Task.DRUMMING_SOMETHING);

        Trillian trillian = new Trillian();
        trillian.interactWith(zaphod, Task.STOPPING_PERSON);

        zaphod.interactWith(cp, Task.DRUMMING_SOMETHING);


    }
}