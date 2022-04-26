package com.aetherwars.card;

import com.aetherwars.util.Type;

public abstract class Card {
    private final String name;
    private final String desc;
    private final Type type;
    private final String imagepath;
    private final int mana;

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

    public abstract String getDescription();

    // Show card
    public abstract void show(); // buat dipanggil oleh card container
}
