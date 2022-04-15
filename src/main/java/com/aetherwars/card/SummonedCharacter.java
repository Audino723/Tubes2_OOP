package com.aetherwars.card;

import com.aetherwars.util.Type;
import com.aetherwars.util.Status;
import java.util.ArrayList;

public class SummonedCharacter {
    private int level;
    private int exp;
    private int totalAttack;
    private int totalHealth;
    private ArrayList<PotionSpellCard> activePotions;
    private CharacterCard character;
    private int swapDurationLeft;

    // Constructor
    public SummonedCharacter(CharacterCard character, int level, int exp) {
        this.character = character;
        this.level = level;
        this.exp = exp;
        this.totalAttack = character.getBaseAtk() + (level-1) * character.getAttackUp();
        this.totalHealth = character.getBaseHp() + (level-1) * character.getHealthUp();
        this.activePotions = new ArrayList<>();
    }

    // Getters and Setters
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getExp() {
        return this.exp;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getTotalAttack() {
        return this.totalAttack;
    }
    public void setTotalAttack(int totalAttack) {
        this.totalAttack = totalAttack;
    }
    public int getTotalHealth() {
        return this.totalHealth;
    }
    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public ArrayList<PotionSpellCard> getActivePotions() {
        return this.activePotions;
    }
    public void addActivePotions(PotionSpellCard activePotion) {
        this.activePotions.add(activePotion);
    }

    public void levelUp() {
        this.level++;
        this.exp = 0;
        this.totalAttack += character.getAttackUp();
        this.totalHealth += character.getHealthUp();
    }

    public boolean isDead() {
        return this.totalHealth <= 0;
    }

    // NO NEED TO UPDATE TOTAL HEALTH AND TOTAL ATTACK (INTEGRATED WHEN CALLING takeAttack() AND takeSpell())
    public void countTotalHealth() {
        // Counts the total health of the character upon using all active spells
        int totalHealth = this.character.getBaseHp() + (level - 1) * character.getHealthUp();
        for (PotionSpellCard p : this.activePotions) {
            totalHealth += p.getHP();
        }
        this.totalHealth = totalHealth;
    }

    public void countTotalAttack() {
        // Counts the total attack of the character upon using all active spells
        int totalAttack = this.character.getBaseAtk() + (level - 1) * character.getAttackUp();
        for (PotionSpellCard p : this.activePotions) {
            totalAttack += p.getAttack();
        }
        this.totalAttack = totalAttack;
    }

    public int takeAttack(SummonedCharacter other) {
        int damage = other.getTotalAttack();
        int expAdded = other.getLevel();

        if ((this.character.getType() == Type.NETHER && other.character.getType() == Type.OVERWORLD) ||
            (this.character.getType() == Type.OVERWORLD && other.character.getType() == Type.END) ||
            (this.character.getType() == Type.END && other.character.getType() == Type.NETHER)) {
            damage = damage / 2; // round down damage
        }
        else if (this.character.getType() != other.character.getType()) {
            damage = damage * 2;
        }

        this.totalHealth -= damage;
        if (this.totalHealth <= 0) {
            // character dies
            this.character.setHp(0);
        }
        else {
            // damage sufficient, only taking from potions' hp and health itselves
            // Top = back
            while (this.activePotions.size() > 0 && damage > 0) {
                int hp = this.activePotions.get(this.activePotions.size() - 1).getHP();
                if (hp > damage) {
                    this.activePotions.get(this.activePotions.size() - 1).setHP(hp - damage);
                    damage = 0;
                }
                else {
                    damage -= hp;
                    this.activePotions.remove(this.activePotions.size() - 1);
                }
            }
        }
        return expAdded;
    }
    public void attack(SummonedCharacter other) {
        // To attack, just call attack(), no need calling takeAttack()
        this.exp += other.takeAttack(this);
    }

    public void updatePotionsTime() {
        if (this.isDead()) {
            this.activePotions.clear();
            System.out.println("Character dead");
            return;
        }
        else {
            // Apply spells effect for specific duration (TEMP) or permanenty (PERM)
            int i = 0;
            while (this.activePotions.size() > 0 && i < this.activePotions.size()) {
                Status temp = this.activePotions.get(i).decreaseDuration();
                if (temp == Status.INACTIVE) {
                    this.activePotions.remove(i);
                }
                else {
                    i++;
                }
            }
        }
    }

    // Take spell, if Potion add to ActivePotions using addActivePotions()
    public void takeSpell(SpellCard s) {
        // SpellCard s is added to a CharacterCard this
        ArrayList<Object> ret = new ArrayList<>();
        if(s.getType() == Type.PTN) {
            if (s instanceof PotionSpellCard) {
                PotionSpellCard sNew = (PotionSpellCard) s;
                this.addActivePotions(sNew);
                ret = sNew.giveEffect();
                this.totalAttack += (Integer) ret.get(0);
                this.totalHealth += (Integer) ret.get(1);
            }
            if (this.isDead()) {
                this.character.setHp(0);
                this.activePotions.clear();
                System.out.println("Character dead");
                return;
            }
        }
        else if (s.getType() == Type.LVL) {
            ret = s.giveEffect();
            this.level += (Integer) ret.get(0);
            this.exp += (Integer) ret.get(1);
            if (this.isDead()) {
                this.character.setHp(0);
                this.activePotions.clear();
                System.out.println("Character dead");
                return;
            }
        }
        else if (s.getType() == Type.SWAP) {
            if (swapDurationLeft == 0){
                int temp = this.character.getBaseAtk();
                this.character.setAtk(this.character.getBaseHp());
                this.character.setHp(temp);
                this.countTotalAttack();
                this.countTotalHealth();
                if (this.isDead()) {
                    this.character.setHp(0);
                    this.activePotions.clear();
                    System.out.println("Character dead");
                    return;
                }
            }
            this.swapDurationLeft = s.getDuration();
        }
        else if (s.getType() == Type.MORPH) {
            ret = s.giveEffect();
            this.character.setName((String) ret.get(0));
            this.character.setDesc((String) ret.get(1));
            this.character.setType((Type) ret.get(2));
            this.character.setImagePath((String) ret.get(3));
            this.character.setMana((Integer) ret.get(4));
            this.character.setAtk((Integer) ret.get(5));
            this.character.setHp((Integer) ret.get(6));
            this.character.setAttackUp((Integer) ret.get(7));
            this.character.setHealthUp((Integer) ret.get(8));
            this.level = 1;
            this.exp = 0;
            this.swapDurationLeft = 0;
            this.activePotions.clear();
            this.countTotalAttack();
            this.countTotalHealth();
        }
    }
}
