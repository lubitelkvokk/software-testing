package se.ifmo.part3.human;

import se.ifmo.part3.tech.ControlPanel;
import se.ifmo.part3.tech.buttons.ButtonType;

import java.util.UnknownFormatConversionException;

public class Zaphod extends Actor {

    @Override
    public void interactWith(Object object, Task action) throws Exception {
        switch (action) {
            case DRUMMING_SOMETHING -> {
                if (object instanceof ControlPanel) {
                    if (!getTask().equals(Task.STOPPED)) {
                    /*
                    Tapping with four fingers
                     */
                        for (int i = 0; i < 4; i++) {
                            tapOnThePanel((ControlPanel) object);
                        }
                    }
                    else{
                        System.out.println(Zaphod.this + " has stopped");
                    }
                } else {
                    throw new UnknownFormatConversionException("Unsupported subject to interacting");
                }
            }
            default -> throw new UnsupportedOperationException("Undefined interacting action");

        }
    }

    public void tapOnThePanel(ControlPanel controlPanel) throws Exception {
        int randInd = (int) (Math.random() * controlPanel.getButtons().size());
        ButtonType bt = controlPanel.getButtons().get(randInd).getButtonType();
        double probability = Math.random();
        // not every knock contributes to the pressing of a button
//        if (probability > 0.9) controlPanel.execute(bt);
        if (probability > 0.9) use(controlPanel, bt);
    }

}
