package model;

import com.aetherwars.model.Player;
import com.aetherwars.card.Card;
import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.card.SpellCard;
import com.aetherwars.util.CardRepo;
import com.aetherwars.util.CommandType;
import com.aetherwars.util.Type;

import org.junit.jupiter.api.BeforeAll;
import com.aetherwars.util.CardReader;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerTest {
    Player p1;
    Player p2;

    @BeforeAll
    public void makeAll() {
        try{
            CardRepo repo = CardReader.read();
            p1 = new Player("ayang 1", repo);
            p2 = new Player("ayang 2", repo);
        } catch (Exception e){

        }

    }

    @Test
    @Order(1)
    public void testName() throws Exception {
        assertEquals("ayang 1", p1.getName());
        assertEquals("ayang 2", p2.getName());
    }

    @Test
    @Order(2)
    public void testStartTurn() throws Exception{
        // Memulai round pertama
        p1.startTurn(1);
        p2.startTurn(1);

        // Jumlah kartu di hand sebanyak 3
        assertEquals(3, p1.getHand().getNeff());

        // Mana awal 1
        assertEquals(1, p1.getMana());
    }

    @Test
    @Order(3)
    public void testDrawnDeck() throws Exception{   
        int initialDeckSize = p1.getDeck().getNeff();

        // Mengambil satu kartu dan sisa 4 kartu
        p1.drawDeck("D1 + H4");

        // Ukuran Deck berkurang dua, jumlah kartu di hand menjadi dua
        assertEquals(4, p1.getHand().getNeff());
        assertEquals(initialDeckSize - 1, p1.getDeck().getNeff());
    }

    @Test
    @Order(4)
    public void testPutCardOnBoard() throws Exception{
        // Memberikan character card dan 10 mana
        p1.startTurn(10);
        CharacterCard card = new CharacterCard.CharacterBuilder()
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
        p1.getHand().add(card);
        p1.handToBoard(p2, "H5 + A3", 1);

        // Jumlah kartu di hand berkurang satu dan jumlah kartu di board menjadi satu
        assertEquals(4, p1.getHand().getNeff());
        assertEquals(1, p1.getBoard().getNeff());
    }

    @Test
    @Order(5)
    public void testAttack() throws Exception{

        /* Melakukan attack pada player musuh*/
        int p2InitialHealth = p2.getHp();
        p1.attack(p2, "A3 + B0");
        assertNotEquals(p2InitialHealth, p2.getHp());

        /* Melakukan attack pada summoend character musuh */
        // Memberikan character card dan 10 mana pada player 2
        p2.startTurn(10);
        CharacterCard card = new CharacterCard.CharacterBuilder()
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
        p2.getHand().add(card);
        p2.handToBoard(p1, "H4 + B3", 2);
        assertEquals(1, p1.getBoard().getNeff());

        // Melakukan attack dari p1 ke p2
        int p1SummonedCharInitialHp = p2.getBoard().getSummonedCharacter(2).getTotalHealth();
        p1.attack(p2, "A3 + B3");

        // Setelah attack health summoend character musuh berkurang
        assertEquals(1, p1.getBoard().getNeff());
        assertNotEquals(p1SummonedCharInitialHp, p2.getBoard().getSummonedCharacter(2).getTotalHealth());
    }

    @Test
    @Order(6)
    public void testLevelUpWithMana() throws Exception{
        /* Memberikan character card dan 10 mana pada player 2 */
        p2.startTurn(10);
        CharacterCard card = new CharacterCard.CharacterBuilder()
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
        p2.getHand().add(card, 4);
        p2.handToBoard(p1, "H5 + B4", 2);

        /* Menaikkan level summoned character pada board B4 */
        int index = 3;
        int p2InitialSummonedCharacterLevel = p2.getBoard().getSummonedCharacter(index).getLevel();
        p2.levelUpSummonedWithMana("MN + B4");
        assertEquals(p2InitialSummonedCharacterLevel + 1, p2.getBoard().getSummonedCharacter(index).getLevel());

    }

    @Test
    @Order(7)
    public void testThrowCard() throws Exception{
        // Membuang satu kartu di board sisa 0 kartu
        // Membuang satu kartu dari hand sisa 3 kartu
        p1.throwCardOnBoard("TC + A3");
        p1.throwCardOnHand("TC + H1");

        
        // Jumlah kartu di hand berkurang satu dan jumlah kartu di board menjadi satu
        assertEquals(3, p1.getHand().getNeff());
        assertEquals(0, p1.getBoard().getNeff());
    }


    
}
