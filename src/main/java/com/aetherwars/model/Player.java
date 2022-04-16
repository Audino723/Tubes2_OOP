package com.aetherwars.model;

import com.aetherwars.card.Card;

import java.util.HashMap;

public class Player {
    private String name;
    private int hp;
    private int mana;
    private Deck deck;
    private Hand hand;
    private Board board;

    // Nitip, nanti buat dipass ke deck
    Player(HashMap<String, Card> cdict) {
        deck = new Deck(cdict);
    }

    public void doOneTurn() {
    }

    public void draw() {
        /*  Dari ambil 1 kartu dari deck
         *   Misal:
         *   this.hand.add(this.deck.take());
         * */
    }

    public void handToBoard() {
        /*  Bisa kayak misal:
         *   this.board.add(hand.take());
         * */
    }

    public void showHand(int i) { // Bisa pass params index
        // this.hand.show(i);
    }

    public void showBoard(int i) { // Bisa pass params index
        // this.board.show(i);
    }

    public void takeAttack(Card c) {

    }

    public void attack(Player p, Card c) {
        p.takeAttack(c);
    }
}
