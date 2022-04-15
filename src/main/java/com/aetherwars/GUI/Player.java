package com.aetherwars.GUI;

public class Player {
    int id;
    int health;
    int mana;
    String[] board;
    String[] hand;
    String[] deck;

    Player(int id){
        this.id = id;
        this.health = 80;
        this.mana = 0;
        this.board = new String[5];
        this.hand = new String[5];
        this.deck = new String[5];
    }

    public void attack(Player other){
        other.health -= 10;
    }
}
