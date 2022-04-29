package utils;

import com.aetherwars.card.CharacterCard;
import com.aetherwars.util.CardReader;
import com.aetherwars.util.CardRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardReaderTest {

    @Test
    public void readTest() {
        try {
            CardRepo repo = CardReader.read();
            assertNotNull(repo);
        } catch (Exception e) {
        }
        ;

    }

    @Test
    public void getCardTest() {
        try {
            CardRepo repo = CardReader.read();
            assertEquals(repo.GetCard("1").getClass(), CharacterCard.class);
        } catch (Exception e) {
        }
        ;
    }
}
