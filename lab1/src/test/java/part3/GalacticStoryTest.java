package part3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ifmo.part3.human.Task;
import se.ifmo.part3.human.Trillian;
import se.ifmo.part3.human.Zaphod;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Ship;
import se.ifmo.part3.tech.buttons.Button;
import se.ifmo.part3.tech.buttons.ExplosionButton;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.UnknownFormatConversionException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GalacticStoryTest {
    private Zaphod zaphod;
    private Trillian trillian;
    private ControlPanel controlPanel;
    private Ship ship;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        zaphod = new Zaphod();
        trillian = new Trillian();
        ExplosionButton explosionButton = new ExplosionButton();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(explosionButton);
        buttons.add(new Button());
        ship = new Ship(buttons);
        controlPanel = ship.getControlPanel();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void stopTrillianDrumming() throws Exception {
        trillian.interactWith(zaphod, Task.STOPPING_PERSON);
        Assertions.assertEquals(trillian.getTask(), Task.STOPPING_PERSON);
        Assertions.assertEquals(zaphod.getTask(), Task.STOPPED);
        for (int i = 0; i < 1000; i++) {
            zaphod.interactWith(controlPanel, Task.DRUMMING_SOMETHING);
            Assertions.assertTrue(outputStreamCaptor.toString().contains("has stopped"));
            outputStreamCaptor.reset();
        }
    }

    @Test
    public void testDrumming() {
        assertThrows(Exception.class, () -> {
            while (true) {
                zaphod.interactWith(controlPanel, Task.DRUMMING_SOMETHING);
            }
        });
    }

    @Test
    public void testUndefinedInteracting() {
        assertThrows(UnsupportedOperationException.class, () -> zaphod.interactWith(controlPanel, Task.SLEEPING));
        assertThrows(UnknownFormatConversionException.class, () -> zaphod.interactWith(new Object(), Task.DRUMMING_SOMETHING));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
