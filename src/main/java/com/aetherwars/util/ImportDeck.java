package com.aetherwars.util;

import com.aetherwars.AetherWars;
import com.aetherwars.card.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class ImportDeck {
    private static final String PLAYER1_FILE_PATH = "card/player/player1.csv";
    private static final String PLAYER2_FILE_PATH = "card/player/player2.csv";

    public static Stack<Card> read(CardRepo repo,String deckname) throws IOException, URISyntaxException {
        Stack<Card> cards = new Stack<>();
        File cardCSVFile;
        String path = "card/deck/";
        path+=deckname;
        try {
            cardCSVFile = new File(Objects.requireNonNull(AetherWars.class.getResource(path)).toURI());
            CSVReader cardReader = new CSVReader(cardCSVFile, "\t");
            cardReader.setSkipHeader(true);
            List<String[]> cardRows = cardReader.read();
            for(String[] row : cardRows){
                cards.push((Card) repo.GetCard(row[0]));
            }
        } catch (Exception e) {
            Random rand = new Random();
            int totalInitialCards = 40 + rand.nextInt(20);

            for (int i = 0; i < totalInitialCards; i++) {
                cards.push(repo.getRandomCard());
            }
        }     
        return cards;
    }

}
