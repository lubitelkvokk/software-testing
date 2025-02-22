package se.ifmo.part3.tech.buttons;

import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.exceptions.SomethingExplodedException;

public class MoveForwardButton extends Button {

    public MoveForwardButton() {
        this.buttonType = ButtonType.MOVE_FORWARD_BUTTON;
    }

    @Override
    public void execute(Object object) throws Exception {
        // cast obj to ship generally
        if (object instanceof Ship) {
            ((Ship) object).moveForward(1);
        } else {
            throw new Exception("Undefined machine for managment");
        }
    }
}
