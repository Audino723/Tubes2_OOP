package card;

import com.aetherwars.card.SwapSpellCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class SwapSpellCardTest {
    
    private SwapSpellCard card;

    @BeforeEach
    public void setUp() {
        card = new SwapSpellCard.SwapBuilder()
                    .setCardName("Swap Spell")
                    .setCardDescription("Swap Spell Card")
                    .setCardImagePath("None")
                    .setCardDuration(1)
                    .setCardMana(3)
                    .getResult();
    }

    @Test
    public void testName(){
        assertEquals(card.getName(), "Swap Spell");
    }

    @Test
    public void testDesc(){
        assertEquals(card.getDesc(), "Swap Spell Card");
    }

    @Test
    public void testImagePath(){
        assertEquals(card.getImagePath(), "None");
    }

    @Test
    public void testMana(){
        assertEquals(3,card.getMana());
    }
    
    @Test
    public void testEffect(){
        assertEquals(1,this.card.giveEffect().get(0));
    }
}
