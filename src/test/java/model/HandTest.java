package model;

import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.LevelSpellCard;
import com.aetherwars.card.SpellCard;
import com.aetherwars.exceptions.EmptyContainerException;
import com.aetherwars.exceptions.FullContainerException;
import com.aetherwars.exceptions.NoCardChosenException;
import com.aetherwars.exceptions.SpaceFilledException;
import com.aetherwars.model.Hand;
import com.aetherwars.util.Type;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class HandTest {
    static Hand hand;

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
    static void makeAll() {
        hand = new Hand();
    }

    @Test
    public void testAdd() throws FullContainerException {
        CharacterCard c1 = makeCard();
        hand.add(c1);
        assertEquals(1, hand.getNeff());
    }

    @Test
    public void testAddwithIndex() throws FullContainerException, SpaceFilledException {
        SpellCard sc2 = makeSpell();

        hand.add(sc2, 3);
        assertEquals(2, hand.getNeff());
        assertEquals(hand.getCard(3), sc2);
    }

    @Test
    public void testTake() throws EmptyContainerException {
        hand.take();
        assertEquals(1, hand.getNeff());
    }

    @Test
    public void testTakewithIndex() throws NoCardChosenException {
        hand.take(3);
        assertEquals(0, hand.getNeff());
        assertNull(hand.getCard(3));
    }

}
