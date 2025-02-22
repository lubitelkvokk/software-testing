package se.ifmo.part3.tech.buttons;

import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.State;
import se.ifmo.part3.tech.exceptions.SomethingExplodedException;

public class ExplosionButton extends Button {

    public ExplosionButton() {
        this.buttonType = ButtonType.EXPLOSION_BUTTON;
    }

    @Override
    public void execute(Object object) throws SomethingExplodedException {
        // cast obj to ship generally
        ((Ship)object).setState(State.BROKEN);
        throw new SomethingExplodedException();
    }

}
