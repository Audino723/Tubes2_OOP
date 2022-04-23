package com.aetherwars.model;

import com.aetherwars.card.Card;

import java.util.*;

public class Player {
    private String name;
    private int hp;
    private int mana;
    private Deck deck;
    private Hand hand;
    private Board board;

    // Nitip, nanti buat dipass ke deck
    Player(String name, HashMap<String, Card> cdict) {
        this.name = name;
        this.hp = 80;
        this.mana = 0;
        this.board = new Board();
        this.hand = new Hand();
        this.deck = new Deck(cdict);
    }

    public String getName()
    {
        return this.name;
    }

    public int getHp()
    {
        return this.hp;
    }

    public int getMana()
    {
        return this.mana;
    }

    public Hand getHand()
    {
        return this.hand;
    }

    public Board getBoard()
    {
        return this.board;
    }

    public void setMana(int rounds){
        this.mana = Math.max(10, rounds);
    }

    
    public void startTurn(int rounds) {
        //Check Hp
        if (this.hp <= 0)
        {
            //Player mati
        }
        
        // else
        setMana(rounds);
    }

    public ArrayList<Card> showDrawnDeck() {
        /*  Mnampilkan drawn-deck */
        
        ArrayList<Card> drawnDeck = this.deck.draw();
        return drawnDeck;
    }
    

    public void drawDeck(int choosenIndex, ArrayList<Card> drawnDeck) {
        try 
        {
            this.hand.add(drawnDeck.get(choosenIndex));
            this.deck.shuffle();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void handToBoard(int choosenIndex) {
        /*  Bisa kayak misal:
         *   this.board.add(hand.take());
         * */
        try
        {
            this.board.take(choosenIndex);
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public void showHand(int i) { // Bisa pass params index
        // this.hand.show(i);
        try
        {
            this.hand.show(i);
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public void showBoard(int i) { // Bisa pass params index
        // this.board.show(i);
        try
        {
            this.board.show(i);
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public void takeAttack(Card c) {
        
    }

    public void attack(Player p, Card c) {
        p.takeAttack(c);
    }
}
