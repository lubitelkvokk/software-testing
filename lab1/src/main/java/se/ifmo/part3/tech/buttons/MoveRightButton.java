package se.ifmo.part3.tech.buttons;

import se.ifmo.part3.tech.Ship;

public class MoveRightButton extends Button {

    public MoveRightButton() {
        this.buttonType = ButtonType.MOVE_RIGHT_BUTTON;
    }

    @Override
    public void execute(Object object) throws Exception {
        // cast obj to ship generally
        if (object instanceof Ship) {
            ((Ship) object).moveRight(1);
        } else {
            throw new Exception("Undefined machine for managment");
        }
    }
}
