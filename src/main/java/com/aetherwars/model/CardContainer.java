package com.aetherwars.model;

import com.aetherwars.card.Card;

public interface CardContainer {
    void add(Card c);

    Card take();

}
