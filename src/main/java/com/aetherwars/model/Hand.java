package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.exceptions.*;

public class Hand implements CardContainer {
    private Card[] cards;
    private int neff;

    Hand() {
        this.cards = new Card[5];
        this.neff = 0;
    }

    public int getNeff() {
        return this.neff;
    }

    public void add(Card c) throws FullContainerException {
        /* Add card di tempat kosong pertama */
        if (this.neff == 5) {
            throw new FullContainerException();
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.cards[i] == null) {
                    this.cards[i] = c;
                    return;
                }
            }
        }
    }

    public void add(Card c, int i) throws SpaceFilledException, FullContainerException {
        /* Add card sesuai indeks */
        if (this.cards[i] != null) {
            throw new SpaceFilledException();
        } else if (this.neff == 5) {
            throw new FullContainerException();
        } else {
            this.cards[i] = c;
            this.neff++;
        }
    }

    public Card take() throws EmptyContainerException {
        /* Ambil card di tempat tidak kosong pertama */
        if (this.neff == 0) {
            throw new EmptyContainerException();
        } else {
            for (int i = 0; i < 5; i++) {
                if (this.cards[i] != null) {
                    Card tmp = this.cards[i];
                    this.cards[i] = null;
                    this.neff--;
                    return tmp;
                }
            }
        }

        return null;
    }

    public Card take(int i) throws NoCardChosenException {
        /* Ambil card sesuai indeks */
        if (this.cards[i] == null) {
            throw new NoCardChosenException();
        } else {
            Card tmp = this.cards[i];
            this.cards[i] = null;
            this.neff--;

            return tmp;
        }
    }

    /* Kalau mau diambil sebagai character (untuk pindahin ke board) */
    public SummonedCharacter takeAsCharacter() throws EmptyContainerException, NotCharacterCardException {
        Card c = this.take();
        if (c instanceof CharacterCard) {
            CharacterCard cc = (CharacterCard) c;
            return new SummonedCharacter(cc, 1, 0);
        } else {
            throw new NotCharacterCardException();
        }
    }

    public SummonedCharacter takeAsCharacter(int i) throws NoCardChosenException, NotCharacterCardException {
        Card c = this.take(i);
        if (c instanceof CharacterCard) {
            CharacterCard cc = (CharacterCard) c;
            return new SummonedCharacter(cc, 1, 0);
        } else {
            throw new NotCharacterCardException();
        }
    }

    public void show(int i) throws NoCardChosenException {
        if (this.cards[i] == null) {
            throw new NoCardChosenException();
        } else {
            this.cards[i].show();
        }
    }

    // To debug
    public void showAll() {

    }
}
