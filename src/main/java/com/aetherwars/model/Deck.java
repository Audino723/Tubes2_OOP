package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.exceptions.EmptyContainerException;
import com.aetherwars.exceptions.FullContainerException;
import com.aetherwars.util.CardRepo;
import com.aetherwars.util.ImportDeck;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Deck implements CardContainer {
    private final Stack<Card> cards;

    Deck(CardRepo repo) {
        this.cards = new Stack<>();

        Random rand = new Random();
        int totalInitialCards = 40 + rand.nextInt(20);

        for (int i = 0; i < totalInitialCards; i++) {
            this.cards.push(repo.getRandomCard());
        }
    }

    Deck(CardRepo repo, String deckname) throws IOException, URISyntaxException{
        this.cards = ImportDeck.read(repo, deckname);
    }

    public int getNeff() {
        return this.cards.size();
    }

    public void add(Card c) throws FullContainerException {
        /* Menambahkan 1 kartu ke atas deck */
        if (this.cards.size() == 60) {
            throw new FullContainerException();
        } else {
            this.cards.push(c);
        }
    }

    public void add(ArrayList<Card> cArr) throws FullContainerException {
        /* Memasukkan array of card ke dalam deck
         * Usage: setelah draw, player ambil 1 dari array,
         * sisa array dapat dimasukkan kembali ke dalam deck menggunakan ini
         * */
        for (Card c : cArr) {
            this.add(c);
        }
    }

    public Card take() throws EmptyContainerException {
        /* Mengambil 1 kartu teratas */
        if (!this.cards.isEmpty()) {
            return this.cards.pop();
        } else {
            throw new EmptyContainerException();
        }
    }
    // NB: TIDAK MENYEDIAKAN take(index) KARENA DECK ADALAH STACK!

    public ArrayList<Card> draw() {
        /* Mengambil 3 kartu (kalau deck masih mencukupi) untuk diambil
         * oleh player saat draw phase */
        int count = 0;
        ArrayList<Card> tmpArr = new ArrayList<>();
        while (count < 3 && !this.cards.isEmpty()) {
            tmpArr.add(this.cards.pop());
            count++;
        }
        return tmpArr;
    }

    public void shuffle() {
        /* Untuk melakukan shuffle setelah draw phase */
        Collections.shuffle(this.cards);
    }

    // To debug
    public void showAll() {

    }

}
