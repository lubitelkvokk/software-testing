package se.ifmo.part3.human;

import lombok.Data;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Executable;
import se.ifmo.part3.tech.buttons.ButtonType;

import java.util.ArrayList;

@Data
public class Actor {
    private ArrayList<Qualities> qualities;
    private Task task;

    public Actor() {
        task = Task.NOTHING;
    }

    public void use(Executable runner, Object arg) throws Exception {
        runner.execute(arg);
    }

    public void interactWith(Object object, Task action) throws Exception {
        throw new UnsupportedOperationException("Not supported actor behavior.");
    }

}


