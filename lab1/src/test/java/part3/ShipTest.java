package part3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import se.ifmo.part3.tech.Locator;
import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.State;
import se.ifmo.part3.tech.buttons.*;
import se.ifmo.part3.tech.exceptions.SomethingExplodedException;
import se.ifmo.part3.tech.exceptions.UndefinedButtonBehaviorException;

import java.util.ArrayList;

public class ShipTest {
    private Ship ship;

    @BeforeEach
    public void setUp() {
        ArrayList<Button> buttons = new ArrayList<>();
        ExplosionButton explosionButton = new ExplosionButton();
        buttons.add(explosionButton);
        buttons.add(new Button());
        buttons.add(new MoveForwardButton());
        buttons.add(new MoveBackwardButton());
        buttons.add(new MoveLeftButton());
        buttons.add(new MoveRightButton());

        ship = new Ship(buttons);
        Locator locator = new Locator();
        ship.setLocator(locator);

    }


    // =========
    // GRAY BOX
    // =========
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 25.613, 362})
    public void moveForward(double x) {
        ship.moveForward(x);
        Assertions.assertEquals(ship.getLocator().getX(), x);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 25.613, 362})
    public void moveBackward(double x) {
        ship.moveBackward(x);
        Assertions.assertEquals(ship.getLocator().getX(), -x);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 25.613, 362})
    public void moveLeft(double y) {
        ship.moveLeft(y);
        Assertions.assertEquals(ship.getLocator().getY(), -y);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 25.613, 362})
    public void moveRight(double y) {
        ship.moveRight(y);
        Assertions.assertEquals(ship.getLocator().getY(), y);
    }


    // =========
    // BLACK BOX
    // =========
    @Test
    public void moveForwardThroughPanel() throws Exception {
        ship.getControlPanel().execute(ButtonType.MOVE_FORWARD_BUTTON);
        Assertions.assertEquals(ship.getLocator().getX(), 1);
    }

    @Test
    public void moveBackwardThroughPanel() throws Exception {
        ship.getControlPanel().execute(ButtonType.MOVE_BACKWARD_BUTTON);
        Assertions.assertEquals(ship.getLocator().getX(), -1);
    }

    @Test
    public void moveLeftThroughPanel() throws Exception {
        ship.getControlPanel().execute(ButtonType.MOVE_LEFT_BUTTON);
        Assertions.assertEquals(ship.getLocator().getY(), -1);
    }

    @Test
    public void moveRightThroughPanel() throws Exception {
        ship.getControlPanel().execute(ButtonType.MOVE_RIGHT_BUTTON);
        Assertions.assertEquals(ship.getLocator().getY(), 1);
    }

    // =========
    // WHITE BOX
    // =========
    @Test
    public void shipExplodeCheck() {
        Assertions.assertThrows(SomethingExplodedException.class, () -> ship.getControlPanel().execute(ButtonType.EXPLOSION_BUTTON));
        Assertions.assertEquals(ship.getState(), State.BROKEN);
    }

}
