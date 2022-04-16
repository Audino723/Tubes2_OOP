package com.aetherwars.exceptions;

public class FullContainerException extends Exception {
    public FullContainerException() {
        super("Container is full! You should throw one card.");
    }
}