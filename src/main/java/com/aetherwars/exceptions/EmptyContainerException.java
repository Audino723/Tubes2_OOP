package com.aetherwars.exceptions;

public class EmptyContainerException extends Exception {
    public EmptyContainerException() {
        super("Container is empty! Can't take anything!?");
    }
}
