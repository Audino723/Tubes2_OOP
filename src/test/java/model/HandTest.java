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
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HandTest {
    private static Hand hand;
    private static CharacterCard ccard;
    private static SpellCard scard;


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
    public static void makeAll() throws FullContainerException, SpaceFilledException {
        hand = new Hand();
        ccard = makeCard();
        scard = makeSpell();

        hand.add(ccard, 2);
        hand.add(scard, 4);
    }

    @Test
    @Order(1)
    public void testAdd() throws FullContainerException {
        hand.add(ccard);
        assertEquals(3, hand.getNeff());
    }

    @Test
    @Order(2)
    public void testAddwithIndex() throws FullContainerException, SpaceFilledException {
        hand.add(scard, 3);
        assertEquals(4, hand.getNeff());
        assertEquals(hand.getCard(3), scard);
    }

    @Test
    @Order(3)
    public void testTake() throws EmptyContainerException {
        hand.take();

        assertEquals(3, hand.getNeff());
    }

    @Test
    @Order(4)
    public void testTakewithIndex() throws NoCardChosenException {
        hand.take(4);

        assertEquals(2, hand.getNeff());
        assertNull(hand.getCard(4));
    }

}
