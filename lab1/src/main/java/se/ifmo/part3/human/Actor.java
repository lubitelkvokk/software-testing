package se.ifmo.part3.human;

import lombok.Data;
import se.ifmo.part3.human.exceptions.ActorUnavailableActionException;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Executable;
import se.ifmo.part3.tech.buttons.ButtonType;

import java.util.ArrayList;
import java.util.UnknownFormatConversionException;

@Data
public class Actor {
    private ArrayList<Qualities> qualities;
    private Task task;
    private String name;

    public Actor(String name) {
        task = Task.NOTHING;
        this.name = name;
    }

    public void use(Executable runner, Object arg) throws Exception {
        runner.execute(arg);
    }

    public void interactWith(Object object, Task action) throws Exception {
        switch (action) {
            case STOPPING_PERSON -> {
                ((Actor) object).setTask(Task.STOPPED);
                setTask(Task.STOPPING_PERSON);
            }
            case DRUMMING_SOMETHING -> {
                if (object instanceof ControlPanel) {
                    if (!task.equals(Task.STOPPED)) {
                        tapOnThePanel((ControlPanel) object, 0.9);
                    } else {
                        throw new ActorUnavailableActionException();
                    }
                } else {
                    throw new UnknownFormatConversionException("Unsupported subject to interacting");
                }
            }
            default -> throw new UnsupportedOperationException("Undefined interacting action");
        }
    }

    public void tapOnThePanel(ControlPanel controlPanel, double probability) throws Exception {
        int randInd = (int) (Math.random() * controlPanel.getButtons().size());
        ButtonType bt = controlPanel.getButtons().get(randInd).getButtonType();
        if (Math.random() > probability) use(controlPanel, bt);
    }
}


