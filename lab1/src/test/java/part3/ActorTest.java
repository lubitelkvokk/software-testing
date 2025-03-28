package part3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ifmo.part3.human.Actor;
import se.ifmo.part3.human.Qualities;
import se.ifmo.part3.human.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class ActorTest {
    private Actor actor;

    @BeforeEach
    public void setUp() {
        actor = new Actor("Actor");

    }
    // ========
    // GRAY BOX
    // ========
    @Test
    public void setQualitiesCheck() {
        Assertions.assertNull(actor.getQualities());
        actor.setQualities(new ArrayList<>(Arrays.asList(Qualities.BRAVADO, Qualities.RESTLESSNESS, Qualities.ARROGANCE, Qualities.ARROGANCE)));
        Assertions.assertNotNull(actor.getQualities());
        Assertions.assertEquals(actor.getQualities().get(0), Qualities.BRAVADO);
    }


    // ========
    // WHITE BOX
    // ========
    @Test
    public void getUndefinedBehavior() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            actor.interactWith(new String(), Task.SLEEPING);
        });
    }



}
