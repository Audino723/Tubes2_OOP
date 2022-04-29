package card;

import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.MorphSpellCard;
import com.aetherwars.util.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MorphSpellCardTest {

    private MorphSpellCard card;

    @BeforeEach
    public void setUp() {
        CharacterCard cc = new CharacterCard.CharacterBuilder()
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

        card = new MorphSpellCard.MorphBuilder()
                .setCardName("Morph Spell")
                .setCardDescription("Morph Spell Card")
                .setCardImagePath("None")
                .setCardMana(2)
                .setCharToChange(cc)
                .getResult();
    }

    @Test
    public void testName() {
        assertEquals(card.getName(), "Morph Spell");
    }

    @Test
    public void testDesc() {
        assertEquals(card.getDesc(), "Morph Spell Card");
    }

    @Test
    public void testImagePath() {
        assertEquals(card.getImagePath(), "None");
    }

    @Test
    public void testMana() {
        assertEquals(2, card.getMana());
    }

    @Test
    public void testEffect() {
        CharacterCard result = (CharacterCard) card.giveEffect().get(0);
        // assertEquals(result.getClass(), card.getCharToChange().getClass());
        assertEquals(result.getName(), "Dummy");
    }
}
