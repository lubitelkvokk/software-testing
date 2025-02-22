package se.ifmo.part3.human;

public class Trillian extends Actor {
    public void interactWith(Object object, Task action) throws Exception {
        switch (action) {
            case STOPPING_PERSON -> {
                ((Actor) object).setTask(Task.STOPPED);
                setTask(Task.STOPPING_PERSON);
            }
        }
    }
}