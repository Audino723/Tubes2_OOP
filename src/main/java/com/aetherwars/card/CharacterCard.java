package com.aetherwars.card;

import com.aetherwars.util.Type;

public class CharacterCard extends Card {
    private int baseAtk; // base
    private int baseHp;
    private int attackUp; // up
    private int healthUp;

    // Constructor
    public CharacterCard(CharacterBuilder builder) {
        super(builder.name, builder.desc, builder.type, builder.imagepath, builder.mana);
        this.baseAtk = builder.baseAtk;
        this.baseHp = builder.baseHp;
        this.attackUp = builder.attackUp;
        this.healthUp = builder.healthUp;
    }

    // Getter
    protected int getBaseAtk() {
        return this.baseAtk;
    }
    protected int getBaseHp() {
        return this.baseHp;
    }
    protected int getAttackUp() {
        return this.attackUp;
    }
    protected int getHealthUp() {
        return this.healthUp;
    }

    // Setter
    protected void setAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }
    protected void setHp(int baseHp) {
        this.baseHp = baseHp;
    }
    protected void setAttackUp(int attackUp) {
        this.attackUp = attackUp;
    }
    protected void setHealthUp(int healthUp) {
        this.healthUp = healthUp;
    }

    // Show
    public void show() {
        System.out.println("Name: " + this.getName());
        System.out.println("Desc: " + this.getDesc());
        System.out.println("Type: " + this.getType());
        System.out.println("ImagePath: " + this.getImagePath());
        System.out.println("Mana: " + this.getMana());
        System.out.println("Atk: " + this.getBaseAtk());
        System.out.println("Hp: " + this.getBaseHp());
    }

    public static class CharacterBuilder{
        private String name;
        private String desc;
        private Type type;
        private String imagepath;
        private int mana;
        private int baseAtk;
        private int baseHp;
        private int attackUp;
        private int healthUp;

        public CharacterBuilder setCardName(String name) {
            this.name = name;
            return this;
        }

        public CharacterBuilder setCardDescription(String desc) {
            this.desc = desc; return this;
        }

        public CharacterBuilder setCardType(Type type) {
            this.type = type; return this;
        }

        public CharacterBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath; return this;
        }

        public CharacterBuilder setCardMana(int mana) {
            this.mana = mana; return this;
        }

        public CharacterBuilder setCardAtk(int atk) {
            this.baseAtk = atk; return this;
        }

        public CharacterBuilder setCardHp(int hp) {
            this.baseHp = hp; return this;
        }

        public CharacterBuilder setCardAtkUp(int atkUp) {
            this.attackUp = atkUp; return this;
        }

        public CharacterBuilder setCardHpUp(int hpUp) {
            this.healthUp = hpUp; return this;
        }

        public CharacterCard getResult() {
            return new CharacterCard(this);
        }
    }
}