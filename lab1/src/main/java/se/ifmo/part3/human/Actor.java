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
//        switch (action) {
//            case STOPPING_PERSON -> {
//                ((Actor) object).setTask(Task.NOTHING);
//                break;
//            }
//            case DRUMMING_SOMETHING -> {
//                if (object.getClass().equals(ControlPanel.class)) {
//                    Math.random()
//                } else {
//                    throw new Exception("Undefined interacting action");
//                }
//            }


    }

}


