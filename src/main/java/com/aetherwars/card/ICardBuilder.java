package com.aetherwars.card;

public interface ICardBuilder <T>{
    public T setCardName(String name);
    public T setCardDescription(String description);
    public T setCardImagePath(String imagePath);
    public T setCardMana(int mana);
    public <U extends Card> U getResult();
}