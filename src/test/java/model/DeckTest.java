package model;

import com.aetherwars.card.Card;
import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.LevelSpellCard;
import com.aetherwars.exceptions.EmptyContainerException;
import com.aetherwars.exceptions.FullContainerException;
import com.aetherwars.model.Deck;
import com.aetherwars.util.CardReader;
import com.aetherwars.util.CardRepo;
import com.aetherwars.util.Type;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class DeckTest {
    static Deck deck;

    public static CharacterCard makeCard() {
        return new CharacterCard.CharacterBuilder()
                .setCardName("Dummy")
                .setCardDescription("Dummy Character")
                .setCardType(Type.NETHER)
                .setCardImagePath("")
                .setCardMana(2)
                .setCardAtk(4)
                .setCardHp(5)
                .setCardAtkUp(1)
                .setCardHpUp(1)
                .getResult();
    }

    public static LevelSpellCard makeSpell() {
        return new LevelSpellCard.LevelSpellBuilder()
                .setCardName("Level Spell")
                .setCardDescription("Level Spell Card")
                .setCardImagePath("None")
                .setIncreaseLevel(true)
                .setCardMana(1)
                .getResult();
    }

    @BeforeAll
    static void makeAll() throws IOException, URISyntaxException {
        CardRepo repo = CardReader.read();
        Deck deck = new Deck(repo);
    }

    @Test
    public void testAdd() throws FullContainerException {
        CharacterCard c = makeCard();
        int count = deck.getNeff();
        deck.add(c);

        assertEquals(count + 1, deck.getNeff());
    }

    @Test
    public void testTake() throws EmptyContainerException {
        int count = deck.getNeff();
        Card c = deck.take();

        assertEquals(count - 1, deck.getNeff());
    }

    @Test
    public void testDrawAndAdd() throws FullContainerException {
        int count = deck.getNeff();
        ArrayList<Card> arr = deck.draw();
        assertEquals(3, arr.size());

        arr.get(0);
        deck.add(arr);
        assertEquals(count - 1, deck.getNeff());
    }

    @Test
    public void shuffle() throws EmptyContainerException, FullContainerException {
        int count = 0;
        Boolean same = true;
        while (count < 10 && same) {
            Card topCard = deck.take();
            deck.add(topCard);
            deck.shuffle();

            Card taken = deck.take();
            if (topCard.equals(taken)) {
                count++;
            } else {
                same = false;
            }
        }
        assertFalse(same); // kalo tetap sama, berarti 10x sama
    }
}
