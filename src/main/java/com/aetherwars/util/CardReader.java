package com.aetherwars.util;

import com.aetherwars.AetherWars;
import com.aetherwars.card.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CardReader {
    private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
    private static final String SPELL_MORPH_CSV_FILE_PATH = "card/data/spell_morph.csv";
    private static final String SPELL_PTN_CSV_FILE_PATH = "card/data/spell_ptn.csv";
    private static final String SPELL_SWAP_CSV_FILE_PATH = "card/data/spell_swap.csv";
    private static final String SPELL_LEVEL_CSV_FILE_PATH = "card/data/spell_level.csv";
    private static final String SPELL_IMMUNE_CSV_FILE_PATH = "card/data/spell_immune.csv";
    public static void main(String[] args) throws IOException, URISyntaxException {
        HashMap<String, Card> dict = new CardReader().read();
        for (Card c: dict.values()) c.show();
    }
    public HashMap<String, Card> read() throws IOException, URISyntaxException {
        HashMap<String, Card> cardDict = new HashMap<>();

        File cardCSVFile = new File(Objects.requireNonNull(AetherWars.class.getResource(CHARACTER_CSV_FILE_PATH)).toURI());
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

        File cardCSVFile2 = new File(Objects.requireNonNull(AetherWars.class.getResource(SPELL_MORPH_CSV_FILE_PATH)).toURI());
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

        File cardCSVFile3 = new File(Objects.requireNonNull(AetherWars.class.getResource(SPELL_PTN_CSV_FILE_PATH)).toURI());
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

        File cardCSVFile4 = new File(Objects.requireNonNull(AetherWars.class.getResource(SPELL_SWAP_CSV_FILE_PATH)).toURI());
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

        File cardCSVFile5 = new File(Objects.requireNonNull(AetherWars.class.getResource(SPELL_LEVEL_CSV_FILE_PATH)).toURI());
        CSVReader cardReader5 = new CSVReader(cardCSVFile5, "\t");
        cardReader5.setSkipHeader(true);
        List<String[]> characterRows5 = cardReader5.read();
        for (String[] row : characterRows5) {
            LevelSpellCard s = new LevelSpellCard.LevelSpellBuilder()
                    .setCardName(row[1])
                    .setCardDescription(row[2])
                    .setCardImagePath(row[3])
                    .setIncreaseLevel(Boolean.parseBoolean(row[4]))
                    .setCardMana(Integer.parseInt(row[5]))
                    .getResult();
            cardDict.put(row[0], s);
        }

        File cardCSVFile6 = new File(Objects.requireNonNull(AetherWars.class.getResource(SPELL_IMMUNE_CSV_FILE_PATH)).toURI());
        CSVReader cardReader6 = new CSVReader(cardCSVFile6, "\t");
        cardReader6.setSkipHeader(true);
        List<String[]> characterRows6 = cardReader6.read();
        for (String[] row : characterRows6) {
            ImmuneSpellCard s = new ImmuneSpellCard.ImmuneBuilder()
                    .setCardName(row[1])
                    .setCardDescription(row[2])
                    .setCardImagePath(row[3])
                    .setCardImmunity(Integer.parseInt(row[4]))
                    .setCardMana(Integer.parseInt(row[5]))
                    .getResult();
            cardDict.put(row[0], s);
        }



        return cardDict;
    }

}
