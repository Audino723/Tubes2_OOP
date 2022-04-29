package card;

import com.aetherwars.card.ImmuneSpellCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ImmuneSpellCardTest {

    private ImmuneSpellCard card;

    @BeforeEach
    public void setUp() {
        card = new ImmuneSpellCard.ImmuneBuilder()
                .setCardName("Dummy Spell")
                .setCardDescription("Immune Description")
                .setCardImagePath("None")
                .setCardImmunity(2)
                .setCardMana(5)
                .getResult();
    }

    @Test
    public void testName() {
        assertEquals(card.getName(), "Dummy Spell");
    }

    @Test
    public void testDesc() {
        assertEquals(card.getDesc(), "Immune Description");
    }

    @Test
    public void testImagePath() {
        assertEquals(card.getImagePath(), "None");
    }

    @Test
    public void testMana() {
        assertEquals(5, card.getMana());
    }

    @Test
    public void testImmunity() {
        assertEquals(2, this.card.giveEffect().get(0));
    }
}
