package com.aetherwars.model;

import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.exceptions.EmptyContainerException;
import com.aetherwars.exceptions.FullContainerException;
import com.aetherwars.exceptions.NoCardChosenException;
import com.aetherwars.exceptions.SpaceFilledException;

public class Board {
    private final SummonedCharacter[] characters;
    private int neff;

    public Board() {
        this.characters = new SummonedCharacter[5];
    }

    public int getNeff() {
        return this.neff;
    }

    public Boolean isEmpty() {
        return getNeff() == 0;
    }

    public void updateState() {
        /* PENTING!! UPDATE CHARACTER STATE SETIAP TURN */
        for (SummonedCharacter sc : this.characters) {
            if (sc != null) {
                sc.updatePotionsTime();
            }
        }
    }

    public void updateSummonedCharacter() {
        // Ngecek summoned character yang udah mati
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != null) {
                if (characters[i].isDead()) {
                    characters[i] = null;
                    this.neff -= 1;
                }
            }
        }
    }


    public void add(SummonedCharacter c) throws FullContainerException {
        /* Add karakter di tempat kosong pertama */
        if (this.neff == 5) {
            throw new FullContainerException();
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.characters[i] == null) {
                    this.characters[i] = c;
                    return;
                }
            }
        }
    }

    public void add(SummonedCharacter c, int i) throws SpaceFilledException, FullContainerException {
        /* Add karakter sesuai indeks */
        if (this.characters[i] != null) {
            throw new SpaceFilledException();
        } else if (this.neff == 5) {
            throw new FullContainerException();
        } else {
            this.characters[i] = c;
            this.neff++;
        }
    }

    public SummonedCharacter take() throws EmptyContainerException {
        /* Ambil karakter yang pertama tidak kosong */
        if (this.neff == 0) {
            throw new EmptyContainerException();
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.characters[i] != null) {
                    SummonedCharacter tmp = this.characters[i];
                    this.characters[i] = null;
                    this.neff--;
                    return tmp;
                }
            }
        }
        return null;
    }

    public SummonedCharacter take(int i) throws NoCardChosenException {
        /* Ambil karakter sesuai indeks */
        if (this.characters[i] == null) {
            throw new NoCardChosenException();
        } else {
            SummonedCharacter tmp = this.characters[i];
            this.characters[i] = null;
            this.neff--;

            return tmp;
        }
    }

    public void show(int i) throws NoCardChosenException {
        if (this.characters[i] == null) {
            throw new NoCardChosenException();
        } else {
            this.characters[i].show();
        }
    }

    public CharacterCard getCharacter(int i) {
        if (this.characters[i] == null) {
            return null;
        } else {
            return this.characters[i].getCharacter();
        }
    }

    public SummonedCharacter getSummonedCharacter(int i) {
        if (this.characters[i] == null) {
            return null;
        } else {
            return this.characters[i];
        }
    }

    // To debug
    public void showAll() {
        for (SummonedCharacter summonedCharacter : characters) {
            if (summonedCharacter != null) {
                summonedCharacter.show();
            }
        }
    }

}
