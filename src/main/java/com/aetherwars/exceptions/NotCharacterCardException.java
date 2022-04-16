package com.aetherwars.exceptions;

public class NotCharacterCardException extends Exception {
    public NotCharacterCardException() {
        super("Only character card can be summonned!");
    }
}
