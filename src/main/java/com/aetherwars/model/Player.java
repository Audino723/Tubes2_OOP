package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.card.SpellCard;
import com.aetherwars.util.Type;

import java.util.*;

public class Player {
    private String name;
    private int hp;
    private int mana;
    private Deck deck;
    private Hand hand;
    private Board board;

    // Nitip, nanti buat dipass ke deck
    public Player(String name, HashMap<String, Card> cdict) {
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
        this.mana = Math.min(10, rounds);
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
        //Ngecek jumlah deck
        //Update states
        this.board.updateState();
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
        
        ArrayList<Card> drawnDeck = new ArrayList<Card>();
        try
        {
            drawnDeck = this.deck.draw();
            this.deck.add(drawnDeck);
        } catch (Exception e){
            e.printStackTrace();
        }
        return drawnDeck;
    }
    

    public void drawDeck(String command) {
        int choosenIndex = (int) command.charAt(1) - 49;
        try 
        {
            ArrayList<Card> drawnDeck = this.deck.draw();
            for (int i = 0; i < 3; i++) {
                if (i == choosenIndex)
                {
                    this.hand.add(drawnDeck.get(2-choosenIndex));
                    drawnDeck.get(2-choosenIndex).show();
                    System.out.println("^dipilih");
                }
                else
                {
                    //nambahin balik ke deck
                    this.deck.add(drawnDeck.get(2-i));
                }
            }
            this.deck.shuffle();
            for (int i = 0; i < this.hand.getNeff(); i++) {
                //this.hand.show(i);
                System.out.println();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void replaceHandFromDraw(String command){
        Integer[] indexes = this.playerCommandHandler("REPLACE_HAND_FROM_DRAW", command);

        int choosenIndex = indexes[0];
        int placingIndex = indexes[1];

        try{
            ArrayList<Card> drawnDeck = this.deck.draw();
            for (int i = 0; i < 3; i++) {
                if (i == choosenIndex)
                {
                    this.hand.take(placingIndex);
                    this.hand.add(drawnDeck.get(2-choosenIndex), placingIndex);
                    System.out.println();
                }
                else
                {
                    //nambahin balik ke deck
                    this.deck.add(drawnDeck.get(2-i));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public char chooseCardonHand(int choosenIndex)
    {
        try
        {
            Card  c = this.hand.getCard(choosenIndex);
            if (c instanceof CharacterCard) {
                return 'C';
            }
            
            if (c instanceof SpellCard)
            {       
                return 'S';        
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }

        //Ngeklik kartu kosong
        return '0';
    }

    public void handToBoard(String command) {
        /*  Bisa kayak misal:
         *   this.board.add(hand.take());
         * */

        Integer[] indexes = this.playerCommandHandler("DRAW_FROM_HAND_TO_BOARD", command);

        if (indexes[0] == -1)
        {
            return;
        }

        int choosenIndex = indexes[0];
        int placingIndex = indexes[1];
        try
        {
            Card  c = this.hand.take(choosenIndex);
            if (c instanceof CharacterCard) {
                CharacterCard characterCard = (CharacterCard) c;
                SummonedCharacter summonedCharacter = new SummonedCharacter(characterCard, 1, 0);
                this.board.add(summonedCharacter);
            }
            else
            {
                SpellCard spellCard = (SpellCard) c;
                this.board.take(choosenIndex).takeSpell(spellCard);                
            }
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

    public Integer[] playerCommandHandler(String type, String command){
        Integer[] indexes = new Integer[2];
        
        switch(type) {
            case "DRAW_FROM_HAND_TO_BOARD":
                if (command.charAt(0) == 'H' && command.charAt(5) == 'A') {
                    indexes[0] = (int) command.charAt(1) - 49;
                    indexes[1] = (int) command.charAt(6) - 49;
                }
                else
                {
                    indexes[0] = -1;
                    indexes[1] = -1;
                }
                break;
            case "REPLACE_HAND_FROM_DRAW":
                if (command.charAt(0) == 'D' && command.charAt(5) == 'H') {
                    indexes[0] = (int) command.charAt(1) - 49;
                    indexes[1] = (int) command.charAt(6) - 49;
                }
                else
                {
                    indexes[0] = -1;
                    indexes[1] = -1;
                }
                break;
            default:
              // code block
          }

        return indexes;
    }


}
