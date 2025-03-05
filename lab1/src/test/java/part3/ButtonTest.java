package part3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ifmo.part3.tech.buttons.*;
import se.ifmo.part3.tech.exceptions.UndefinedButtonBehaviorException;

public class ButtonTest {
    private Button button;
    private MoveForwardButton moveForwardButton;
    private MoveBackwardButton moveBackwardButton;
    private MoveLeftButton moveLeftButton;
    private MoveRightButton moveRightButton;

    @BeforeEach
    void setUp() {
        button = new Button();
        moveForwardButton = new MoveForwardButton();
        moveBackwardButton = new MoveBackwardButton();
        moveLeftButton = new MoveLeftButton();
        moveRightButton = new MoveRightButton();
    }

    // ===========
    // WHITE BOXES
    // ===========
    @Test
    void testButton() {
        Assertions.assertThrows(UndefinedButtonBehaviorException.class, () -> button.execute(new Object()));
    }

    @Test
    void testIncorrectMoveForwardButton() {
        Assertions.assertThrows(Exception.class, () -> moveForwardButton.execute(new Object()));
    }

    @Test
    void testIncorrectMoveBackwardButton() {
        Assertions.assertThrows(Exception.class, () -> moveBackwardButton.execute(new Object()));
    }

    @Test
    void testIncorrectMoveRightButton() {
        Assertions.assertThrows(Exception.class, () -> moveLeftButton.execute(new Object()));
    }

    @Test
    void testIncorrectMoveLeftButton() {
        Assertions.assertThrows(Exception.class, () -> moveRightButton.execute(new Object()));
    }
}
