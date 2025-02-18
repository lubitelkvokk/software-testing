package se.ifmo.part3.tech;

import lombok.Data;
import se.ifmo.part3.tech.buttons.Button;
import se.ifmo.part3.tech.buttons.ButtonType;

import java.util.ArrayList;

@Data
public class ControlPanel implements Executable {
    private final ArrayList<Button> buttons;
    private final Ship ship;

    public ControlPanel(Ship ship, ArrayList<Button> buttons) {
        this.ship = ship;
        this.buttons = buttons;
    }

    // TODO replace to DRUM method in actor
    public void pressRandomButton() throws Exception {
        int randInd = (int) (Math.random() * buttons.size());
        buttons.get(randInd).execute(ship);
    }

    /**
     * press button
     *
     * @param object
     * @throws Exception
     */
    @Override
    public void execute(Object object) throws Exception {
        ButtonType buttonType = (ButtonType) object;
        buttons.stream().filter(button -> button.getButtonType().equals(buttonType)).findFirst().orElseThrow().execute(ship);
    }
}
