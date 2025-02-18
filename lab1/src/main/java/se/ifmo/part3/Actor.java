package se.ifmo.part3;

import lombok.Data;
import se.ifmo.part3.tech.Executable;
import se.ifmo.part3.tech.buttons.Button;

import java.util.ArrayList;

@Data
public class Actor {
    private ArrayList<Qualities> qualities;

    public void use(Executable runner, Object arg) throws Exception {
        runner.execute(arg);
    }

}
