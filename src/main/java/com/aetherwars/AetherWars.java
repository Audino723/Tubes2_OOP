package com.aetherwars;

import java.util.HashMap;

import com.aetherwars.GUI.GUI;
import com.aetherwars.card.Card;
import com.aetherwars.model.Player;
import com.aetherwars.util.CardReader;
import com.aetherwars.util.CardRepo;
public class AetherWars {
    public static void main(String[] args) throws Exception {
        CardRepo repo = CardReader.read();
        // for (Card c : dict.values()){
        //     c.show();
        // }
        Player player1 = new Player("Lumine",repo);
        Player player2 = new Player("Ayaka",repo);
        GUI gui = new GUI(player1,player2, repo);
    }
}