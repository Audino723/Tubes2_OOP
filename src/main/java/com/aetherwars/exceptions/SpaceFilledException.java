package com.aetherwars.exceptions;

public class SpaceFilledException extends Exception {
    public SpaceFilledException() {
        super("Can't place the card. That place is not empty!");
    }
}
