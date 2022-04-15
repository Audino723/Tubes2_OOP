package com.aetherwars.card;

import com.aetherwars.util.Type;

public abstract class Card {
    private String name;
    private String desc;
    private Type type;
    private String imagepath;
    private int mana;

    // Constructor
    public Card(String name, String desc, Type type, String imagepath, int mana) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.imagepath = imagepath;
        this.mana = mana;
    }

    // Getter
    protected String getName() {
        return this.name;
    }
    protected String getDesc() {
        return this.desc;
    }
    protected Type getType() {
        return this.type;
    }
    protected String getImagePath() {
        return this.imagepath;
    }
    protected int getMana() {
        return this.mana;
    }

    // Setter
    protected void setName(String name) {
        this.name = name;
    }
    protected void setDesc(String desc) {
        this.desc = desc;
    }
    protected void setType(Type type) {
        this.type = type;
    }
    protected void setImagePath(String imagepath) {
        this.imagepath = imagepath;
    }
    protected void setMana(int mana) {
        this.mana = mana;
    }

    // Show card
    public abstract void show(); // buat dipanggil oleh card container
}
