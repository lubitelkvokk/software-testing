package se.ifmo.part3.tech.buttons;

import se.ifmo.part3.tech.Ship;

public class MoveLeftButton extends Button {

    public MoveLeftButton() {
        this.buttonType = ButtonType.MOVE_LEFT_BUTTON;
    }

    @Override
    public void execute(Object object) throws Exception {
        if (object instanceof Ship) {
            ((Ship) object).moveLeft(1);
        } else {
            throw new Exception("Undefined machine for managment");
        }
    }
}
