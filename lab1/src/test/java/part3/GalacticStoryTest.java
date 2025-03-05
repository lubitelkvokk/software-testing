package part3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ifmo.part3.human.Actor;
import se.ifmo.part3.human.Task;
import se.ifmo.part3.human.exceptions.ActorUnavailableActionException;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.buttons.Button;
import se.ifmo.part3.tech.buttons.ExplosionButton;
import se.ifmo.part3.tech.exceptions.ButtonExceptions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.UnknownFormatConversionException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GalacticStoryTest {
    private Actor zaphod;
    private Actor trillian;
    private ControlPanel controlPanel;
    private Ship ship;

    @BeforeEach
    public void setUp() {
        zaphod = new Actor("Zaphod");
        trillian = new Actor("Trillian");
        ExplosionButton explosionButton = new ExplosionButton();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(explosionButton);
        buttons.add(new Button());
        ship = new Ship(buttons);
        controlPanel = ship.getControlPanel();

    }

    // =========
    // BLACK BOX
    // =========
    @Test
    public void stopTrillianDrumming() throws Exception {
        trillian.interactWith(zaphod, Task.STOPPING_PERSON);
        Assertions.assertEquals(trillian.getTask(), Task.STOPPING_PERSON);
        Assertions.assertEquals(zaphod.getTask(), Task.STOPPED);

        Assertions.assertThrows(ActorUnavailableActionException.class, () -> zaphod.interactWith(controlPanel, Task.DRUMMING_SOMETHING));
    }

    // =========
    // GRAY BOX
    // =========
    @Test()
    public void testDrumming() {
        assertThrows(Exception.class, () -> {
            zaphod.tapOnThePanel(controlPanel, 0);
        });
        try {
            zaphod.interactWith(controlPanel, Task.DRUMMING_SOMETHING);
        } catch (Exception e) {
        }
    }


    // =========
    // WHITE BOX
    // =========
    @Test
    public void testUndefinedInteracting() {
        assertThrows(UnsupportedOperationException.class, () -> zaphod.interactWith(controlPanel, Task.SLEEPING));
        assertThrows(UnknownFormatConversionException.class, () -> zaphod.interactWith(new Object(), Task.DRUMMING_SOMETHING));
    }

}
