package se.ifmo.part3.tech.buttons;

import lombok.Getter;
import se.ifmo.part3.tech.Executable;
import se.ifmo.part3.tech.exceptions.UndefinedButtonBehaviorException;

@Getter
public class Button implements Executable {
    protected ButtonType buttonType = ButtonType.UNKNOWN_BUTTON;
    public Button(){
    }

    @Override
    public void execute(Object object) throws Exception {
        throw new UndefinedButtonBehaviorException();
    }
}
