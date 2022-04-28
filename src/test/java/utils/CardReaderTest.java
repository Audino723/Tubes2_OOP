package utils;

import com.aetherwars.util.CardReader;
import com.aetherwars.util.CardRepo;
import com.aetherwars.card.CharacterCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class CardReaderTest {

    @Test
    public void readTest(){
        try{
            CardRepo repo = CardReader.read();
            assertNotNull(repo);
        }
        catch(Exception e){};
        
    }

    @Test
    public void getCardTest(){
        try{
            CardRepo repo = CardReader.read();
            assertEquals(repo.GetCard("1").getClass(), CharacterCard.class);
        }
        catch(Exception e){};
    }
}
