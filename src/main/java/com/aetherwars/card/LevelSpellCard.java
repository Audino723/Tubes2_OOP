package com.aetherwars.card;

import com.aetherwars.util.Type;
import java.util.ArrayList;

public class LevelSpellCard extends SpellCard {
    public boolean increaseLevel; // True to increase, False for decrease

    public LevelSpellCard(SpellBuilder builder) {
        super(builder.name, builder.desc, Type.LVL, builder.imagepath, builder.mana, 0);
        this.increaseLevel = builder.increaseLevel;
    }

    public boolean isLevelIncrease() {
        return this.increaseLevel;
    }

    public void show() {
        super.show();
        System.out.println("Increase Level: " + this.isLevelIncrease());
    }

    public ArrayList<Object> giveEffect() {
        ArrayList<Object> effect = new ArrayList<Object>();
        if (this.increaseLevel) {
            effect.add(1);
            effect.add(0);
        }
        else {
            effect.add(-1);
            effect.add(0);
        }
        return effect;
    }

    public static class SpellBuilder {
        private boolean increaseLevel;
        private String name;
        private String desc;
        private String imagepath;
        private int mana;
    
        public SpellBuilder setCardName(String name) {
            this.name = name;return this;
        }
    
        public SpellBuilder setCardDescription(String desc) {
            this.desc = desc;return this;
        }
    
        public SpellBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath; return this;
        }
    
        public SpellBuilder setCardMana(int mana) {
            this.mana = mana; return this;
        }

        public SpellBuilder setIncreaseLevel(boolean increaseLevel) {
            this.increaseLevel = increaseLevel; return this;
        }


        public LevelSpellCard getResult() {
            return new LevelSpellCard(this);
        }
    }
}