package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.card.SummonedCharacter;

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

    public Deck getDeck()
    {
        return this.deck;
    }

    public void setMana(int rounds){
        this.mana = Math.max(10, rounds);
    }

    public Boolean startTurn(int rounds) {
        // Check first round
        if (rounds == 1)
        {   
            try 
            {
                ArrayList<Card> drawnDeck = this.deck.draw();
                for (int i = 0; i < 3; i++) {
                    this.hand.add(drawnDeck.get(i));
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        }

        //Check Hp
        //ngecek jumlah deck
        if (this.hp <= 0 && this.deck.getNeff() <= 0)
        {
            return false;
        }
        
        // else
        setMana(rounds);
        return true;
    }

    public ArrayList<Card> showDrawnDeck() {
        /*  Menampilkan drawn-deck */
        
        ArrayList<Card> drawnDeck = this.deck.draw();
        return drawnDeck;
    }
    

    public void drawDeck(int choosenIndex, ArrayList<Card> drawnDeck) {
        try 
        {
            // nanti coba tanpa showDrawnDeck
            for (int i = 0; i < 3; i++) {
                if (i == choosenIndex)
                {
                    this.hand.add(drawnDeck.get(choosenIndex));
                }
                else
                {
                    //nambahin balik ke deck
                    this.deck.add(drawnDeck);
                }
            }
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
            this.board.add(this.hand.takeAsCharacter(choosenIndex));
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public void showHand(int i) { 
        // Bisa pass params index
        // this.hand.show(i);`
        try
        {
            this.hand.show(i);
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public void showBoard(int i) { 
        // Bisa pass params index
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

    public void attack(Player p2, int index1, int index2) {
        try
        {
            // Mengambil character dari board
            SummonedCharacter playerChar = this.board.take(index1);
            SummonedCharacter otherChar = p2.getBoard().take(index2);

            // Attack other
            playerChar.attack(otherChar);

            // Mengembalikan board
            this.board.add(playerChar, index1);
            p2.getBoard().add(otherChar, index2);            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }        
    }
}
