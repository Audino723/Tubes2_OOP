package com.aetherwars.exceptions;

public class NoCardChosenException extends Exception {
    public NoCardChosenException() {
        super("No card is choosen! Aborting.");
    }
}