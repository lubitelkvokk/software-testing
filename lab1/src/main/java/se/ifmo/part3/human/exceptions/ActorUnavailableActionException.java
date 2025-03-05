package se.ifmo.part3.human.exceptions;

public class ActorUnavailableActionException extends Exception{
    public ActorUnavailableActionException(){
        super("Actor unavailable");
    }
}
