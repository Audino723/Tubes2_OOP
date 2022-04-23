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
    public String getName() {
        return this.name;
    }
    public String getDesc() {
        return this.desc;
    }
    public Type getType() {
        return this.type;
    }
    public String getImagePath() {
        return this.imagepath;
    }
    public int getMana() {
        return this.mana;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public void setImagePath(String imagepath) {
        this.imagepath = imagepath;
    }
    public void setMana(int mana) {
        this.mana = mana;
    }

    // Show card
    public abstract void show(); // buat dipanggil oleh card container
}
