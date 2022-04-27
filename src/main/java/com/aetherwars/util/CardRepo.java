package com.aetherwars.util;

import java.util.HashMap;
import java.util.Random;

import com.aetherwars.card.Card;

public class CardRepo {
    HashMap<String, Card> cardDict;
    public CardRepo(){
        cardDict = new HashMap<String,Card>();
    }
    public void InsertCard(String id, Card c){
        cardDict.put(id, c); 
    }

    public void InsertCard(HashMap<String, Card> toAdd){
        cardDict.putAll(toAdd);
    }

    public void DeleteCard(String id){
        cardDict.remove(id);
    }

    public void DeleteCard(HashMap<String, Card> toDelete){
        cardDict.keySet().removeAll(toDelete.keySet());
    }

    public void UpdateCard(String id, Card toChange){
        cardDict.replace(id, toChange);
    }

    public void UpdateCard(HashMap<String, Card> toChange){
        cardDict.putAll(toChange);
    }

    public Card GetCard(String id){
        return cardDict.get(id);
    }

    public Card getRandomCard(){
        Random r = new Random();
        int i = r.nextInt(cardDict.size());
        return cardDict.get(cardDict.keySet().toArray()[i].toString());
    }

    
}
