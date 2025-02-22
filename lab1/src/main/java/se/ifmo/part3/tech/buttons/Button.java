package se.ifmo.part3.tech.buttons;

import lombok.Getter;
import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.Executable;
import se.ifmo.part3.tech.exceptions.SomethingExplodedException;
import se.ifmo.part3.tech.exceptions.UndefinedButtonBehaviorException;

@Getter
public class Button implements Executable {
//    private final ControlPanel controlPanel;
    protected ButtonType buttonType = ButtonType.UNKNOWN_BUTTON;
    public Button(){
//        this.controlPanel = controlPanel;
    }

    @Override
    public void execute(Object object) throws Exception {
//        System.out.println();
        throw new UndefinedButtonBehaviorException();
    }
}
