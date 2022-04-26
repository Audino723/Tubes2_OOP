package com.aetherwars.card;

import java.util.ArrayList;
import com.aetherwars.util.Type;

public class ImmuneSpellCard extends SpellCard {
    public ImmuneSpellCard(ImmuneBuilder builder) {
        super(builder.name, builder.desc, Type.IMMUNE, builder.imagepath, builder.mana, builder.duration);
    }

    public void show() {
        super.show();
        System.out.println("Is Immune");
    }

    public ArrayList<Object> giveEffect() {
        ArrayList<Object> effect = new ArrayList<Object>();
        effect.add(-1);
        return effect;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        String text;
        text = this.getName();
        text += "\n\nTipe       : " + this.getType();
        text += "\nMana     : " + this.getMana();
        text += "\nDuration : " + this.getDuration();
        text += "\n\n"+this.getDesc();
        return text;
    }

    public static class ImmuneBuilder {
        private String name;
        private String desc;
        private String imagepath;
        private int mana;
        private int duration;

        public ImmuneBuilder setCardName(String name) {
            this.name = name;return this;
        }
    
        public ImmuneBuilder setCardDescription(String desc) {
            this.desc = desc;return this;
        }

        public ImmuneBuilder setCardDuration(int duration) {
            this.duration = duration;return this;
        }
    
        public ImmuneBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath; return this;
        }
    
        public ImmuneBuilder setCardMana(int mana) {
            this.mana = mana; return this;
        }

        public ImmuneSpellCard getResult() {
            return new ImmuneSpellCard(this);
        }
    }
}
