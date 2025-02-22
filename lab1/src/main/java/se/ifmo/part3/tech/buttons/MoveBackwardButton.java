package se.ifmo.part3.tech.buttons;

import se.ifmo.part3.tech.Ship;

public class MoveBackwardButton extends Button {

    public MoveBackwardButton() {
        this.buttonType = ButtonType.MOVE_BACKWARD_BUTTON;
    }

    @Override
    public void execute(Object object) throws Exception {
        // cast obj to ship generally
        if (object instanceof Ship) {
            ((Ship) object).moveBackward(1);
        } else {
            throw new Exception("Undefined machine for managment");
        }
    }
}
