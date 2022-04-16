package com.aetherwars.model;

import com.aetherwars.card.Card;
import com.aetherwars.exceptions.EmptyContainerException;
import com.aetherwars.exceptions.FullContainerException;

public interface CardContainer {
    void add(Card c) throws FullContainerException;

    Card take() throws EmptyContainerException;

}
