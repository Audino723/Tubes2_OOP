package model;

import com.aetherwars.card.CharacterCard;
import com.aetherwars.card.SummonedCharacter;
import com.aetherwars.exceptions.EmptyContainerException;
import com.aetherwars.exceptions.FullContainerException;
import com.aetherwars.exceptions.NoCardChosenException;
import com.aetherwars.exceptions.SpaceFilledException;
import com.aetherwars.model.Board;
import com.aetherwars.util.Type;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class BoardTest {
    static Board board;
    static CharacterCard card;

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

    public SummonedCharacter makeSummon(CharacterCard cc) {
        return new SummonedCharacter(cc, 1, 0);
    }

    @BeforeAll
    static void makeAll() {
        board = new Board();
        card = makeCard();
    }

    @Test
    public void testAdd() throws FullContainerException {
        SummonedCharacter sc1 = makeSummon(card);
        board.add(sc1);
        assertEquals(1, board.getNeff());
    }

    @Test
    public void testAddwithIndex() throws FullContainerException, SpaceFilledException {
        SummonedCharacter sc2 = makeSummon(card);

        board.add(sc2, 3);
        assertEquals(2, board.getNeff());
        assertEquals(board.getSummonedCharacter(3), sc2);
    }

    @Test
    public void testTake() throws EmptyContainerException {
        board.take();
        assertEquals(1, board.getNeff());
    }

    @Test
    public void testTakewithIndex() throws NoCardChosenException {
        board.take(3);
        assertEquals(0, board.getNeff());
        assertNull(board.getCharacter(3));
    }
}
