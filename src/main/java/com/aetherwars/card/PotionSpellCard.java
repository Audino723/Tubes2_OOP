package com.aetherwars.card;

import com.aetherwars.util.Type;
import java.util.ArrayList;

public class PotionSpellCard extends SpellCard {
    private int hp; // Health points (for PotionSpellCard)
    private int attack; // Attack points (for PotionSpellCard)

    // id	name	description	imagepath	attack	hp	mana	duration
    // PotionSpellCard(row[1], row[2], Type.PTN, row[3], row[4], row[6], row[5], row[7])
    public PotionSpellCard(PotionBuilder builder) {
        super(builder.name, builder.desc, Type.PTN, builder.imagepath, builder.mana, builder.duration);
        this.hp = builder.hp;
        this.attack = builder.atk;
    }

    public PotionSpellCard(String name, String desc, Type type, String imagepath, int mana, int duration, int hp, int atk) {
        super(name,desc, type,imagepath,mana,duration);
        this.hp = hp;
        this.attack = atk;
    }

    public PotionSpellCard copyCard() {
        return new PotionSpellCard(this.getName(), this.getDesc(), Type.PTN, this.getImagePath(), this.getMana(), this.getDuration(), this.getHP(), this.getAttack());
    }

    public int getHP() {
        return this.hp;
    }
    public int getAttack() {
        return this.attack;
    }
    public void setHP(int hp) {
        this.hp = hp;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void show() {
        super.show();

        System.out.println("HP: " + this.getHP());
        System.out.println("Attack: " + this.getAttack());
    }

    public ArrayList<Object> giveEffect() {
        ArrayList<Object> effect = new ArrayList<Object>();
        effect.add(this.getHP());
        effect.add(this.getAttack());
        return effect;
    }

    @Override
    public String getDescription() {
        String text;
        text = this.getName();
        text += "\n\nTipe    : " + this.getType();
        text += "\nMana  : " + this.getMana();
        text += "\nHp      : " + this.getHP();
        text += "\nAttack : " + this.getAttack();
        text += "\nDuration : " + this.getDuration();
        text += "\n\n"+this.getDesc();
        return text;
    }

    public static class PotionBuilder implements ICardBuilder<PotionBuilder>, IStatsBuilder<PotionBuilder> {
        private String name;
        private String desc;
        private String imagepath;
        private int mana;
        private int atk;
        private int hp;
        private int duration;

        public PotionBuilder setCardName(String name) {
            this.name = name; return this;
        }

        public PotionBuilder setCardDescription(String desc) {
            this.desc = desc;return this;
        }

        public PotionBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath;return this;
        }

        public PotionBuilder setCardMana(int mana) {
            this.mana = mana;return this;
        }

        public PotionBuilder setCardAtk(int atk) {
            this.atk = atk;return this;
        }

        public PotionBuilder setCardHp(int hp) {
            this.hp = hp;return this;
        }

        public PotionBuilder setCardDuration(int duration){
            this.duration = duration;return this;
        }

        @SuppressWarnings("unchecked")
        public PotionSpellCard getResult() {
            return new PotionSpellCard(this);
        }
    }
}