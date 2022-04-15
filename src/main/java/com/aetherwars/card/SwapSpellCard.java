package com.aetherwars.card;

import com.aetherwars.util.Type;

import java.util.ArrayList;

public class SwapSpellCard extends SpellCard {

    // id	name	description	imagepath	duration	mana
    // SwapSpellCard(row[1], row[2], Type.SWAP, row[3], row[5], row[4])
    public SwapSpellCard(SwapBuilder builder) {
        super(builder.name, builder.desc, Type.SWAP, builder.imagepath, builder.mana, builder.duration);
    }
    
    @Override
    ArrayList<Object> giveEffect() {
        return null;
    }

    public void show() {
        super.show();
    }

    public static class SwapBuilder{
        private String name;
        private String desc;
        private Type type;
        private String imagepath;
        private int mana;
        private int duration;

        public SwapBuilder setCardName(String name) {
            this.name = name; return this;
        }

        public SwapBuilder setCardDescription(String desc) {
            this.desc = desc;return this;
        }

        public SwapBuilder setCardType(Type type) {
            this.type = type;return this;
        }

        public SwapBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath;return this;
        }

        public SwapBuilder setCardMana(int mana) {
            this.mana = mana;return this;
        }

        public SwapBuilder setCardDuration(int duration) {
            this.duration = duration;return this;
        }

        public SwapSpellCard getResult() {
            return new SwapSpellCard(this);
        }
    }
}