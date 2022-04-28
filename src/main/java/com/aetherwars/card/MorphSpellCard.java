package com.aetherwars.card;

import com.aetherwars.util.Type;

import java.util.ArrayList;

public class MorphSpellCard extends SpellCard {
    private CharacterCard charToChange;

    // id	name	description	imagepath	targetid	mana
    // MorphSpellCard(row[1], row[2], Type.MORPH, row[3], row[5], 0, cardDict.get(row[4]));
    public MorphSpellCard(MorphBuilder builder) {
        super(builder.name, builder.desc, Type.MORPH, builder.imagepath, builder.mana, 0);
        this.charToChange = builder.charToChange;
    }

    @Override
    public ArrayList<Object> giveEffect() {
        ArrayList<Object> ret = new ArrayList<Object>();
        ret.add(this.charToChange);
        return ret;
    }

    public CharacterCard getChar(){
        return this.charToChange;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        String text;
        text = this.getName();
        text += "\n\nTipe      : " + this.getType();
        text += "\nMana    : " + this.getMana();
        text += "\nCharacter To Change : " + this.charToChange.getName();
        text += "\n\n"+this.getDesc();
        return text;
    }
    


    @Override
    public void show() {
        super.show();
        System.out.println("Target: " + this.charToChange.getName());
    }

    public static class MorphBuilder implements ICardBuilder<MorphBuilder>{
        private String name;
        private String desc;
        private String imagepath;
        private int mana;
        private CharacterCard charToChange;
    
        public MorphBuilder setCardName(String name) {
            this.name = name;return this;
        }
    
        public MorphBuilder setCardDescription(String desc) {
            this.desc = desc; return this;
        }
    
        public MorphBuilder setCardImagePath(String imagepath) {
            this.imagepath = imagepath; return this;
        }
    
        public MorphBuilder setCardMana(int mana) {
            this.mana = mana; return this;
        }
    
        public MorphBuilder setCharToChange(CharacterCard charToChange) {
            this.charToChange = charToChange; return this;
        }
    
        @SuppressWarnings("unchecked")
        public MorphSpellCard getResult() {
            return new MorphSpellCard(this);
        }
    }
}