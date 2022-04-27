package com.aetherwars.card;

import com.aetherwars.util.Type;

public class CharacterCard extends Card {
    private final int baseAtk; // base
    private final int baseHp;
    private final int attackUp; // up
    private final int healthUp;

    // Constructor
    public CharacterCard(CharacterBuilder builder) {
        super(builder.name, builder.desc, builder.type, builder.imagepath, builder.mana);
        this.baseAtk = builder.baseAtk;
        this.baseHp = builder.baseHp;
        this.attackUp = builder.attackUp;
        this.healthUp = builder.healthUp;
    }

    // Getter
    public int getBaseAtk() {
        return this.baseAtk;
    }
    public int getBaseHp() {
        return this.baseHp;
    }
    public int getAttackUp() {
        return this.attackUp;
    }
    public int getHealthUp() {
        return this.healthUp;
    }

    @Override
    public String getDescription() {
        String text;
        text = this.getName();
        text += "\n\nTipe    : " + this.getType();
        text += "\nMana  : " + this.getMana();
        text += "\nHp      : " + this.getBaseHp();
        text += "\nAttack : " + this.getBaseAtk();
        text += "\n\n"+this.getDesc();
        return text;
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

    public static class CharacterBuilder implements ICardBuilder<CharacterBuilder>, ITypeBuilder<CharacterBuilder>, IStatsBuilder<CharacterBuilder>{
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

        public CharacterBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath; return this;
        }

        public CharacterBuilder setCardMana(int mana) {
            this.mana = mana; return this;
        }

        public CharacterBuilder setCardType(Type type) {
            this.type = type; return this;
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

        @SuppressWarnings("unchecked")
        public CharacterCard getResult() {
            return new CharacterCard(this);
        }
    }


}