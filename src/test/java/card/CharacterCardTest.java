package card;

import com.aetherwars.card.CharacterCard;
import com.aetherwars.util.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CharacterCardTest {

    CharacterCard card;

    @BeforeEach
    public void setUp() {
        card = new CharacterCard.CharacterBuilder()
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


    @Test
    public void testMana() {
        assertEquals(2, this.card.getMana());
    }

    @Test
    public void testDesc() {
        assertEquals(card.getDesc(), "Dummy Character");
    }

    @Test
    public void testType() {
        assertEquals(card.getType(), Type.NETHER);
    }

    @Test
    public void testImagePath() {
        assertEquals(card.getImagePath(), "");
    }

    @Test
    public void testName() {
        assertEquals(card.getName(), "Dummy");
    }

    @Test
    public void testAtk() {
        assertEquals(card.getBaseAtk(), 4);
    }

    @Test
    public void testHp() {
        assertEquals(card.getBaseHp(), 5);
    }

    @Test
    public void testAtkUp() {
        assertEquals(card.getAttackUp(), 1);
    }

    @Test
    public void testHpUp() {
        assertEquals(card.getHealthUp(), 1);
    }
}
