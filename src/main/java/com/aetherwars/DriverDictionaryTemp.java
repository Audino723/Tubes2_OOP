package com.aetherwars;

import com.aetherwars.card.*;
import com.aetherwars.util.CSVReader;
import com.aetherwars.util.Type;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.HashMap;
import java.util.Objects;

public class DriverDictionaryTemp {
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SPELL_MORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
    private static final String SPELL_PTN_CSV_FILE_PATH = "card/data/spell_ptn.csv";
    private static final String SPELL_SWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";

    public static void main(String[] args) throws URISyntaxException, IOException {

        HashMap<String, Card> cardDict = new HashMap<>();

        File cardCSVFile = new File(Objects.requireNonNull(DriverDictionaryTemp.class.getResource(CHARACTER_CSV_FILE_PATH)).toURI());
        CSVReader cardReader = new CSVReader(cardCSVFile, "\t");
        cardReader.setSkipHeader(true);
        List<String[]> characterRows = cardReader.read();
        
        for (String[] row : characterRows) {
            CharacterCard c = new CharacterCard.CharacterBuilder()
                    .setCardName(row[1])
                    .setCardDescription(row[3])
                    .setCardType(Type.valueOf(row[2]))
                    .setCardImagePath(row[4])
                    .setCardMana(Integer.parseInt(row[7]))
                    .setCardAtk(Integer.parseInt(row[5]))
                    .setCardHp(Integer.parseInt(row[6]))
                    .setCardAtkUp(Integer.parseInt(row[8]))
                    .setCardHpUp(Integer.parseInt(row[9]))
                    .getResult();
            cardDict.put(row[0], c);
        }
        
        File cardCSVFile2 = new File(Objects.requireNonNull(DriverDictionaryTemp.class.getResource(SPELL_MORPH_CSV_FILE_PATH)).toURI());
        CSVReader cardReader2 = new CSVReader(cardCSVFile2, "\t");
        cardReader2.setSkipHeader(true);
        List<String[]> cardRows2 = cardReader2.read();
        for (String[] row : cardRows2) {
            MorphSpellCard m = new MorphSpellCard.MorphBuilder()
                    .setCardName(row[1])
                    .setCardDescription(row[2])
                    .setCardImagePath(row[3])
                    .setCardMana(Integer.parseInt(row[5]))
                    .setCharToChange((CharacterCard)cardDict.get(row[4]))
                    .getResult();
            cardDict.put(row[0], m);
        }

        File cardCSVFile3 = new File(Objects.requireNonNull(DriverDictionaryTemp.class.getResource(SPELL_PTN_CSV_FILE_PATH)).toURI());
        CSVReader cardReader3 = new CSVReader(cardCSVFile3, "\t");
        cardReader3.setSkipHeader(true);
        List<String[]> characterRows3 = cardReader3.read();
        for (String[] row : characterRows3) {
            PotionSpellCard p = new PotionSpellCard.PotionBuilder()
                    .setCardName(row[1])
                    .setCardDescription(row[2])
                    .setCardImagePath(row[3])
                    .setCardAtk(Integer.parseInt(row[4]))
                    .setCardHp(Integer.parseInt(row[5]))
                    .setCardMana(Integer.parseInt(row[6]))
                    .setCardDuration(Integer.parseInt(row[7]))
                    .getResult();
            cardDict.put(row[0], p);
        }

        File cardCSVFile4 = new File(Objects.requireNonNull(DriverDictionaryTemp.class.getResource(SPELL_SWAP_CSV_FILE_PATH)).toURI());
        CSVReader cardReader4 = new CSVReader(cardCSVFile4, "\t");
        cardReader4.setSkipHeader(true);
        List<String[]> characterRows4 = cardReader4.read();
        for (String[] row : characterRows4) {
            SwapSpellCard s = new SwapSpellCard.SwapBuilder()
                    .setCardName(row[1])
                    .setCardDescription(row[2])
                    .setCardImagePath(row[3])
                    .setCardDuration(Integer.parseInt(row[4]))
                    .setCardMana(Integer.parseInt(row[5]))
                    .getResult();
            cardDict.put(row[0], s);
        }

        // // Create a new charactercard
        // CharacterCard characterCard = new CharacterCard("Zombie", "PvZ", Type.NETHER, "path", 1, 10, 20, 2, 3, 5, 10);
        // CharacterCard characterCard2 = new CharacterCard("Groot", "Marvel", Type.OVERWORLD, "path", 4, 15, 50, 6, 7, 8, 9);
        
        // // Create a new spell card
        // LevelSpellCard levelSpellCard = new LevelSpellCard("halo", "halohalo", Type.LVL, "path", 4, 1, 10, 2, 2, 2);
        
        // // Create a new potion spell card
        // PotionSpellCard potionSpellCard = new PotionSpellCard("hai", "haihai", Type.PTN, "path", 1, 1, 10, 5, 2, 1);
        
        // characterCard.show();
        // System.out.println();
        // levelSpellCard.show();
        // System.out.println();
        // potionSpellCard.show();
        // System.out.println();

        // System.out.println(characterCard.countTotalAttack());
        // System.out.println(characterCard.countTotalHealth());
        // characterCard.attack(characterCard2);
        // System.out.println(characterCard2.takeAttack(characterCard));
    }
}
