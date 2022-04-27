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

    @Override
    public String getDescription() {
        String text;
        text = this.getName();
        text += "\n\nTipe       : " + this.getType();
        text += "\nMana     : " + this.getMana();
        text += "\nDuration : " + this.getDuration();
        text += "\n\n"+this.getDesc();
        return text;
    }

    public static class SwapBuilder implements ICardBuilder<SwapBuilder>{
        private String name;
        private String desc;
        private String imagepath;
        private int mana;
        private int duration;

        public SwapBuilder setCardName(String name) {
            this.name = name; return this;
        }

        public SwapBuilder setCardDescription(String desc) {
            this.desc = desc;return this;
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

        @SuppressWarnings("unchecked")
        public SwapSpellCard getResult() {
            return new SwapSpellCard(this);
        }
    }
}