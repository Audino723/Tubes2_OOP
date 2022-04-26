package com.aetherwars.util;

import com.aetherwars.AetherWars;
import com.aetherwars.card.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class ImportDeck {
    private static final String PLAYER1_FILE_PATH = "card/player/player1.csv";
    private static final String PLAYER2_FILE_PATH = "card/player/player2.csv";

    public static Stack<Card> read(Map<String, Card> dict,int player) throws IOException, URISyntaxException {
        Stack<Card> cards = new Stack<>();
        File cardCSVFile;
        if(player == 1) {
            cardCSVFile = new File(Objects.requireNonNull(AetherWars.class.getResource(PLAYER1_FILE_PATH)).toURI());
        }
        else{
            cardCSVFile = new File(Objects.requireNonNull(AetherWars.class.getResource(PLAYER2_FILE_PATH)).toURI());
        }
        CSVReader cardReader = new CSVReader(cardCSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> cardRows = cardReader.read();
        for(String[] row : cardRows){
            cards.push((Card) dict.get(row[0]));
        }
        return cards;
    }

}
