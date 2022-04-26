package com.aetherwars;

import java.util.HashMap;

import com.aetherwars.GUI.GUI;
import com.aetherwars.card.Card;
import com.aetherwars.model.Player;
import com.aetherwars.util.CardReader;
public class AetherWars {
    public static void main(String[] args) throws Exception {
        HashMap<String,Card> dict= new CardReader().read();
        // for (Card c : dict.values()){
        //     c.show();
        // }
        Player player1 = new Player("Lumine",dict,1);
        Player player2 = new Player("Ayaka",dict);
        GUI gui = new GUI(player1,player2);
    }
}