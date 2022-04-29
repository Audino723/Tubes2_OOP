package card;

import com.aetherwars.card.PotionSpellCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PotionSpellCardTest {

    private PotionSpellCard.PotionBuilder cardBuilder;

    @BeforeEach
    public void setUp() {
        cardBuilder = new PotionSpellCard.PotionBuilder()
                .setCardName("Potion Spell")
                .setCardDescription("Potion Spell Card")
                .setCardImagePath("None")
                .setCardMana(1)
                .setCardAtk(2)
                .setCardHp(3);
    }

    @Test
    public void testName() {
        PotionSpellCard card = cardBuilder.getResult();
        assertEquals(card.getName(), "Potion Spell");
    }

    @Test
    public void testDesc() {
        PotionSpellCard card = cardBuilder.getResult();
        assertEquals(card.getDesc(), "Potion Spell Card");
    }

    @Test
    public void testImagePath() {
        PotionSpellCard card = cardBuilder.getResult();
        assertEquals(card.getImagePath(), "None");
    }

    @Test
    public void testMana() {
        PotionSpellCard card = cardBuilder.getResult();
        assertEquals(1, card.getMana());
    }

    @Test
    public void testPositiveStats() {
        PotionSpellCard card = cardBuilder.getResult();
        assertEquals(2, card.getAttack());
        assertEquals(3, card.getHP());
    }

    @Test
    public void testNegativeStats() {
        PotionSpellCard card = cardBuilder.setCardAtk(-1).setCardHp(-5).getResult();
        assertEquals(-1, card.getAttack());
        assertEquals(-5, card.getHP());
    }

    @Test
    public void testModifyandEffect() {
        PotionSpellCard card = cardBuilder.getResult();
        card.setAttack(0);
        card.setHP(1);
        assertEquals(1, card.giveEffect().get(0));
        assertEquals(0, card.giveEffect().get(1));
    }
}
