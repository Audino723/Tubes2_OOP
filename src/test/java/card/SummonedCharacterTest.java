package card;

import com.aetherwars.card.*;
import com.aetherwars.util.Type;
import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;

public class SummonedCharacterTest {
    
    private SummonedCharacter c;
    private SummonedCharacter cother;
    private CharacterCard k1 = new CharacterCard.CharacterBuilder()
                .setCardName("Dummy")
                .setCardDescription("Dummy Character")
                .setCardType(Type.NETHER)
                .setCardImagePath("")
                .setCardMana(2)
                .setCardAtk(5)
                .setCardHp(4)
                .setCardAtkUp(1)
                .setCardHpUp(1)
                .getResult();

    private CharacterCard k2 = new CharacterCard.CharacterBuilder()
                .setCardName("Other Dummy")
                .setCardDescription("Dummy Character NEW")
                .setCardType(Type.NETHER)
                .setCardImagePath("")
                .setCardMana(2)
                .setCardAtk(5)
                .setCardHp(3)
                .setCardAtkUp(1)
                .setCardHpUp(1)
                .getResult();
    
    private ImmuneSpellCard Immunecard = new ImmuneSpellCard.ImmuneBuilder()
                    .setCardName("Immune Spell")
                    .setCardDescription("Immune Description")
                    .setCardImagePath("None")
                    .setCardImmunity(1)
                    .setCardMana(5)
                    .getResult();

    
    private LevelSpellCard LevelCard = new LevelSpellCard.LevelSpellBuilder()
                    .setCardName("Level Spell")
                    .setCardDescription("Level Spell Card")
                    .setCardImagePath("None")
                    .setIncreaseLevel(true)
                    .setCardMana(1)
                    .getResult();

    private MorphSpellCard MorphCard =  new MorphSpellCard.MorphBuilder()
                    .setCardName("Morph Spell")
                    .setCardDescription("Morph Spell Card")
                    .setCardImagePath("None")
                    .setCardMana(2)
                    .setCharToChange(k2)
                    .getResult();
    
    private SwapSpellCard swapCard = new SwapSpellCard.SwapBuilder()
                    .setCardName("Swap Spell")
                    .setCardDescription("Swap Spell Card")
                    .setCardImagePath("None")
                    .setCardDuration(1)
                    .setCardMana(3)
                    .getResult();

    // private SpellCard spellCard = new PotionSpellCard.PotionBuilder()
    //                 .setCardName("Potion Spell" )
    //                 .setCardDescription("Potion Spell Card")
    //                 .setCardImagePath("None")
    //                 .setCardMana(1)
    //                 .setCardAtk(2)
    //                 .setCardHp(3);

    @BeforeEach
    public void setUp() {
        this.c = new SummonedCharacter(k1,1,0); 
        this.cother = new SummonedCharacter(k2,1,0);
    }

    @Test
    public void testConst(){
        assertEquals(c.getCharacter(), k1);
        assertEquals(c.getLevel(), 1);
        assertEquals(c.getExp(), 0);
        assertEquals(c.getTotalAttack(),5);
        assertEquals(c.getTotalHealth(), 4);
    }

    @Test
    public void testLvlUp(){
        c.levelUp();
        assertEquals(c.getTotalAttack(), k1.getAttackUp() + k1.getBaseAtk());
        assertEquals(c.getTotalHealth(), k1.getHealthUp() + k1.getBaseHp());
    }

    @Test
    public void testBothDead(){
        c.attack(cother);
        assertEquals(cother.isDead(), true);
        assertEquals(c.isDead(), true);
    }

    @Test
    public void testImmune(){
        c.takeSpell(Immunecard);
        cother.attack(c);
        assertEquals(c.getTotalHealth(), k1.getBaseHp()+ k1.getHealthUp());
    }

    @Test
    public void testLevelUpCard(){
        c.takeSpell(LevelCard);
        assertEquals(c.getLevel(), 2);
        assertEquals(c.getTotalHealth(), k1.getBaseHp() + k1.getHealthUp());
        assertEquals(c.getTotalAttack(), k1.getBaseAtk() + k1.getAttackUp());
    }

    @Test
    public void testMorphCard(){
        c.takeSpell(MorphCard);
        assertEquals(c.getCharacter(), k2);
    }

    @Test
    public void testSwapCard(){
        c.takeSpell(swapCard);
        assertEquals(c.getTotalHealth(), k1.getBaseAtk());
        assertEquals(c.getTotalAttack(), k1.getBaseHp());
    }


}
