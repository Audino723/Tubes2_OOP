package com.aetherwars.card;

import java.util.ArrayList;

interface SummonCharacter {
    int getLevel();
    int getExp();

    int getTotalAttack();
    int getTotalHealth();

    void addActivePotions(PotionSpellCard activePotion);
    ArrayList<PotionSpellCard> getActivePotions();

    void levelUp();

    boolean isDead();

    // NO NEED IN THE MEANTIME
    void countTotalHealth();
    void countTotalAttack();

    int takeAttack(SummonedCharacter other);
    void attack(SummonedCharacter other);

    void updatePotionsTime();

    // Take spell, if Potion add to ActivePotions using addActivePotions()
    void takeSpell(SpellCard s);
}