package Card;

import com.aetherwars.card.LevelSpellCard;
import com.aetherwars.util.Type;
import org.junit.jupiter.api.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class LevelSpellCardTest {
    
    private LevelSpellCard card;

    @BeforeEach
    public void setUp() {
        card = new LevelSpellCard.LevelSpellBuilder()
                    .setCardName("Level Spell")
                    .setCardDescription("Level Spell Card")
                    .setCardImagePath("None")
                    .setIncreaseLevel(true)
                    .setCardMana(1)
                    .getResult();
    }

    @Test
    public void testName(){
        assertEquals(card.getName(), "Level Spell");
    }

    @Test
    public void testDesc(){
        assertEquals(card.getDesc(), "Level Spell Card");
    }

    @Test
    public void testImagePath(){
        assertEquals(card.getImagePath(), "None");
    }

    @Test
    public void testMana(){
        assertEquals(1,card.getMana());
    }
    
    @Test
    public void testEffect(){
        assertEquals(1,this.card.giveEffect().get(0));
        assertEquals(0, this.card.giveEffect().get(1));
    }
}
