package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.card.SpellCard;
import com.aetherwars.util.CommandType;

import java.io.IOException;
import java.net.URISyntaxException;
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

    public Player(String name, HashMap<String, Card> cdict, int player) throws IOException, URISyntaxException {
        this.name = name;
        this.hp = 80;
        this.mana = 0;
        this.board = new Board();
        this.hand = new Hand();
        this.deck = new Deck(cdict,player);
    }

    public String getName()
    {
        return this.name;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
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

    public Boolean isPlayerDead()
    {        
        if (this.hp <= 0 || this.deck.getNeff() <= 0)
        {
            return true;
        }

        return false;
    }

    public void updateBoard(){
        /* Mengecek status summoned character pada board */ 
        this.board.updateSummonedCharacter();
    }

    public void setMana(int rounds){
        /* Mengatur jumlah mana berdasarkan rounds */
        this.mana = Math.min(10, rounds);
    }

    public Boolean useMana(int mana){
        /* Menggunakan mana (untuk level-up atau spell) */
        if (this.mana - mana >=0)
        {
            this.mana -= mana;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void startTurn(int rounds) {
        /* Memulai Turn */

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

        /*
            For every turn :
            - Update spell states
            - Update mana
            - Shuffle deck
        */
        this.board.updateState();
        setMana(rounds);
        this.deck.shuffle();
    }

    public ArrayList<Card> showDrawnDeck() {
        /*  Menampilkan drawn-deck */
        
        ArrayList<Card> drawnDeck = new ArrayList<Card>();
        try
        {
            drawnDeck = this.deck.draw();
            // setelah add jadi kebalik urutan pertamanya
            this.deck.add(drawnDeck);
        } catch (Exception e){
            e.printStackTrace();
        }
        return drawnDeck;
    }
    

    public void drawDeck(String command) {
        /* Melakukan draw dari deck */

        int choosenIndex = (int) command.charAt(1) - 49;
        try 
        {
            ArrayList<Card> drawnDeck = this.deck.draw();
            for (int i = 0; i < 3; i++) {
                if (i == choosenIndex)
                {
                    this.hand.add(drawnDeck.get(2-choosenIndex));
                }
                else
                {
                    //nambahin balik ke deck
                    this.deck.add(drawnDeck.get(2-i));
                }
            }
            this.deck.shuffle();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void replaceHandFromDraw(String command){
        /* Melakukan draw dari deck ketika hand sudah penuh */
        Integer[] indexes = this.playerCommandHandler(CommandType.REPLACE_HAND_FROM_DRAW, command);

        int choosenIndex = indexes[0];
        int placingIndex = indexes[1];

        try{
            ArrayList<Card> drawnDeck = this.deck.draw();
            for (int i = 0; i < 3; i++) {
                if (i == choosenIndex)
                {
                    this.hand.take(placingIndex);
                    this.hand.add(drawnDeck.get(2-choosenIndex), placingIndex);
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
        /* Mengecek tipe kartu yang dipilih dari hand */
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

    public void throwCardOnHand(String command){
        Integer[] indexes = this.playerCommandHandler(CommandType.THROW_FROM_HAND, command);

        int choosenIndex = indexes[0];

        if (choosenIndex == -1)
        {
            System.out.println("gagal throw card");
            return;
        }

        try {
            // contoh command : "H1 + TH"
            this.hand.take(choosenIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void throwCardOnBoard(String command){
        Integer[] indexes = this.playerCommandHandler(CommandType.THROW_FROM_BOARD, command);

        int choosenIndex = indexes[0];

        if (choosenIndex <= 0)
        {
            System.out.println("gagal throw card");
            return;
        }

        try {
            // contoh command : "B1 + TH"
            this.board.take(choosenIndex-1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handToBoard(Player p2, String command) {
        /*  Mengambil kartu dari hand dan meletakkan ke board*/

        Integer[] indexes = this.playerCommandHandler(CommandType.DRAW_FROM_HAND_TO_BOARD, command);

        if (indexes[0]  <= 0)
        {            
            System.out.println("Gagal mensummmon character");
            return;
        }

        int choosenIndex = indexes[0];
        int placingIndex = indexes[1];
        try
        {   
            // Ambil kartu karakter dan cek penggunaan mana
            Card  c = this.hand.take(choosenIndex);
            if (!useMana(c.getMana()))
            {
                System.out.println("kurang mana woi");
                this.hand.add(c, choosenIndex);
                return;
            }

            if (c instanceof CharacterCard) {
                CharacterCard characterCard = (CharacterCard) c;
                SummonedCharacter summonedCharacter = new SummonedCharacter(characterCard, 1, 0);
                this.board.add(summonedCharacter, placingIndex);

                //DEBUG
                this.board.showAll();
            }
            else
            {
                SpellCard spellCard = (SpellCard) c;
                this.board.getSummonedCharacter(placingIndex).takeSpell(spellCard); 

                // Menggunakan spell offensive
                this.updateBoard();
                p2.updateBoard();               
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public void levelUpSummonedWithMana(String command) {
        /* Melakukan level-up summoned-character dengan mana */

        Integer[] indexes = this.playerCommandHandler(CommandType.LEVEL_UP_SUMMONED_WITH_MANA, command);

        int placingIndex = indexes[1];

        if (!useMana(1))
        {
            // Command Tidak Tepat
            System.out.println("Mana tidak cukup untuk menaikkan level characater");
            return;
        }

        try
        {   
            SummonedCharacter summonedCharacter = this.board.getSummonedCharacter(placingIndex);
            summonedCharacter.addExp(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();   
        }
    }

    public Boolean attack(Player p2, String command) {
        /* Melakukan attack ke player musuh */

        Integer[] indexes = this.playerCommandHandler(CommandType.ATTACK_OTHER, command);
        Boolean isAttackSuccess = false;

        if (indexes[0] == -1)
        {         
            // Command Tidak Tepat   
            System.out.println("Gagal menyerang");
            return isAttackSuccess;
        }

        int choosenIndex = indexes[0];
        int placingIndex = indexes[1];

        try
        {
            if (placingIndex == -1)
            {
                // Menyerang player lawan
                SummonedCharacter playerChar = this.board.getSummonedCharacter(choosenIndex);
                p2.setHp(p2.getHp() - playerChar.getTotalAttack());        
            }
            else
            {
                // Mengambil character dari board
                SummonedCharacter playerChar = this.board.getSummonedCharacter(choosenIndex);
                SummonedCharacter otherChar = p2.getBoard().getSummonedCharacter(placingIndex);

                // Attack other
                playerChar.attack(otherChar);
            }

            this.updateBoard();
            p2.updateBoard();

            isAttackSuccess = true;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }        

        return isAttackSuccess;
    }

    public Integer[] playerCommandHandler(CommandType type, String command){
        /* Command Handling */

        Integer[] indexes = new Integer[2];

        switch(type) {
            case DRAW_FROM_HAND_TO_BOARD:
                if  (command.charAt(0) == 'H' && 
                    command.substring(5,6).matches("[A|B]")) 
                {
                    indexes[0] = (int) command.charAt(1) - 49;
                    indexes[1] = (int) command.charAt(6) - 49;
                }
                else
                {
                    indexes[0] = -1;
                    indexes[1] = -1;
                }
                break;

            case REPLACE_HAND_FROM_DRAW:
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
            case THROW_FROM_BOARD:
                if  (command.substring(0,1).matches("B") && 
                    command.substring(5,7).matches("TC")) 
                {
                    indexes[0] = (int) command.charAt(1) - 49;
                    indexes[1] = -1;
                }
                else
                {
                    indexes[0] = -1;
                    indexes[1] = -1;
                }
                break;
            case THROW_FROM_HAND:
                if  (command.substring(0,1).matches("H") && 
                    command.substring(5,7).matches("TC")) 
                {
                    indexes[0] = (int) command.charAt(1) - 49;
                    indexes[1] = -1;
                }
                else
                {
                    indexes[0] = -1;
                    indexes[1] = -1;
                }
                break;
            case LEVEL_UP_SUMMONED_WITH_MANA:
                if  (command.substring(0,2).matches("MN") && 
                    command.substring(5,6).matches("[A|B]")) 
                {
                    indexes[0] = -1;
                    indexes[1] = (int) command.charAt(6) - 49;
                }
                else
                {
                    indexes[0] = -1;
                    indexes[1] = -1;
                }
                break;
            case ATTACK_OTHER:
                // Character menyerang character lain
                if  ((command.charAt(0) == 'A' && command.charAt(5) == 'B') ||
                    (command.charAt(0) == 'B' && command.charAt(5) == 'A')) 
                {
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
}
