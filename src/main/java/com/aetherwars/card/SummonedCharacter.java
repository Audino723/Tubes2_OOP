package com.aetherwars.card;

import com.aetherwars.util.Status;
import com.aetherwars.util.Type;

import java.util.ArrayList;

public class SummonedCharacter {
    private int level;
    private int exp;
    private int totalAttack;
    private int totalHealth;
    private ArrayList<PotionSpellCard> activePotions;
    private CharacterCard character;
    private int swapDurationLeft;
    private int immuneLeft;
    private int manaChar;

    // Constructor
    public SummonedCharacter(CharacterCard character, int level, int exp) {
        this.character = character;
        this.level = level;
        this.exp = exp;
        this.totalAttack = character.getBaseAtk() + (level - 1) * character.getAttackUp();
        this.totalHealth = character.getBaseHp() + (level - 1) * character.getHealthUp();
        this.activePotions = new ArrayList<>();
        this.immuneLeft = 0;
        this.manaChar = character.getMana();
    }

    // Getters and Setters
    public CharacterCard getCharacter() {
        return character;
    }

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

    public void addExp(int exp){
        this.exp += exp;
        updateLevel();
    }

    public void updateLevel() {
        while (this.exp >= this.getNextLevelExp()) {
            this.exp -= this.getNextLevelExp();
            this.level++;
            this.totalAttack += character.getAttackUp();
            this.totalHealth += character.getHealthUp();
        }
    }

    public int getNextLevelExp() {
        return (1 + (this.level - 1) * 2);
    }

    public boolean isDead() {
        return this.totalHealth <= 0;
    }

    // NO NEED TO UPDATE TOTAL HEALTH AND TOTAL ATTACK (INTEGRATED WHEN CALLING
    // takeAttack() AND takeSpell())
    public void countTotalHealth() {
        // Counts the total health of the character upon using all active spells
        if(this.swapDurationLeft == 0){
            int totalHealth = this.character.getBaseHp() + (level - 1) * character.getHealthUp();
            for (PotionSpellCard p : this.activePotions) {
                totalHealth += p.getHP();
            }
            this.totalHealth = totalHealth;
        }
        else{
            int totalAttack = this.character.getBaseAtk() + (level - 1) * character.getAttackUp();
            for (PotionSpellCard p : this.activePotions) {
                totalAttack += p.getAttack();
            }
            this.totalHealth = totalAttack;
        }
    }

    public void countTotalAttack() {
        // Counts the total attack of the character upon using all active spells
        if(this.swapDurationLeft == 0){
            int totalAttack = this.character.getBaseAtk() + (level - 1) * character.getAttackUp();
            for (PotionSpellCard p  : this.activePotions) {
                totalAttack += p.getAttack();
            }
            this.totalAttack = totalAttack;
        }
        else{
            int totalHealth = this.character.getBaseHp() + (level - 1) * character.getHealthUp();
            for (PotionSpellCard p : this.activePotions) {
                totalHealth += p.getHP();
            }
            this.totalAttack = totalHealth;
        }
        
    }

    public int takeAttack(SummonedCharacter other) {
        if (this.immuneLeft > 0) {
            this.immuneLeft--;
            return 0;
        }
        int damage = other.getTotalAttack();
        int expAdded = other.getLevel();

        if ((this.character.getType() == Type.NETHER && other.character.getType() == Type.OVERWORLD) ||
                (this.character.getType() == Type.OVERWORLD && other.character.getType() == Type.END) ||
                (this.character.getType() == Type.END && other.character.getType() == Type.NETHER)) {
            damage = damage / 2; // round down damage
        } else if (this.character.getType() != other.character.getType()) {
            damage = damage * 2;
        }

        this.totalHealth -= damage;
        if (this.totalHealth <= 0) {
            // character dies
            this.totalHealth = 0;
        } else {
            // damage sufficient, only taking from potions' hp and health itselves
            // Top = back
            while (this.activePotions.size() > 0 && damage > 0) {
                int hp = this.activePotions.get(this.activePotions.size() - 1).getHP();
                if (hp > damage) {
                    this.activePotions.get(this.activePotions.size() - 1).setHP(hp - damage);
                    damage = 0;
                } else {
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
        other.setExp(other.getExp() + this.takeAttack(other));
        if(!this.isDead()) {
            this.updateLevel();
        }
        if(!other.isDead()) {
            other.updateLevel();
        }
    }

    public void updatePotionsTime() {
        if (this.isDead()) {
            this.activePotions.clear();
            System.out.println("Character dead");
            return;
        } else {
            // Apply spells effect for specific duration (TEMP) or permanenty (PERM)
            ArrayList<PotionSpellCard> toRemove = new ArrayList<PotionSpellCard>();
            
            for (PotionSpellCard potion : this.activePotions) {
                Status temp = potion.decreaseDuration();
                System.out.println("STATUS " + temp);
                if (temp == Status.INACTIVE) {
                    toRemove.add(potion);
                    // this.activePotions.remove(potion);
                    // this.countTotalHealth();
                    // this.countTotalAttack();
                }
            }
            this.activePotions.removeAll(toRemove);
            if(this.swapDurationLeft>0){
                this.swapDurationLeft--;
            }
            this.countTotalHealth();
            this.countTotalAttack();

            // int i = 0;
            // while (this.activePotions.size() > 0 && i < this.activePotions.size()) {
            //     Status temp = this.activePotions.get(i).decreaseDuration();
            //     if (temp == Status.INACTIVE) {
            //         ArrayList<Object> ret = this.activePotions.get(i).giveEffect();
            //         this.totalHealth -= (Integer) ret.get(0);
            //         this.totalAttack -= (Integer) ret.get(1);
            //         this.activePotions.remove(i);
            //     } else {
            //         i++;
            //     }
            // } 
        }  
    }

    // Take spell, if Potion add to ActivePotions using addActivePotions()
    public void takeSpell(SpellCard s) {
        // SpellCard s is added to a CharacterCard this
        ArrayList<Object> ret = new ArrayList<>();
        if (s.getType() == Type.PTN) {
            if (s instanceof PotionSpellCard) {
                PotionSpellCard temp = (PotionSpellCard) s;
                PotionSpellCard sNew = temp.copyCard();
                this.addActivePotions(sNew);
                ret = sNew.giveEffect();
                this.totalHealth += (Integer) ret.get(0);
                this.totalAttack += (Integer) ret.get(1);
            }
            if (this.isDead()) {
                this.activePotions.clear();
                System.out.println("Character dead");
                return;
            }
        } else if (s.getType() == Type.LVL) {
            ret = s.giveEffect();
            int tempLevel = this.level;
            //int tempMana = this.manaChar - (int) Math.ceil(tempLevel / 2);
            tempLevel += (Integer) ret.get(0);
            if (tempLevel >= 1 && tempLevel <= 10) {
                this.level = tempLevel;
                this.manaChar = this.manaChar - (int) Math.ceil(tempLevel / 2);
                this.exp = (Integer) ret.get(1);
                this.countTotalAttack();
                this.countTotalHealth();
            } else {
                System.out.println("Level out of range");
            }
            // If level = 1 and leveldown, level stays 1
        } else if (s.getType() == Type.SWAP) {
            ret = s.giveEffect();
            this.swapDurationLeft += (Integer) ret.get(0);
            System.out.println("SWAP DURATION " + this.swapDurationLeft);
            this.countTotalAttack();
            this.countTotalHealth();
        } else if (s.getType() == Type.MORPH) {
            ret = s.giveEffect();
            this.character = (CharacterCard) ret.get(0);
            this.countTotalAttack();
            this.countTotalHealth();
        } else if (s.getType() == Type.IMMUNE) {
            ret = s.giveEffect();
            int tempMana = (int) Math.ceil(this.level / 2);
            if (this.immuneLeft >= 0) {
                this.immuneLeft += (Integer) ret.get(0);
                this.manaChar = tempMana;
            }
        }
    }

    public void show() {

    }
}
