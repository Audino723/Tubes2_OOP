package com.aetherwars.card;

import com.aetherwars.util.Type;
import com.aetherwars.util.Status;
import java.util.ArrayList;

public abstract class SpellCard extends Card {
    private int duration;
    private Status status;

    public SpellCard(String name, String desc, Type type, String image, int mana, int duration) {
        super(name, desc, type, image, mana);
        this.duration = duration;
        this.status = Status.FIRSTROUND;
    }
    
    public int getDuration() {
        return this.duration;
    }
    public Status getStatus() {
        return this.status;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public void show() {
        System.out.println("Name: " + this.getName());
        System.out.println("Desc: " + this.getDesc());
        System.out.println("Type: " + this.getType());
        System.out.println("Image: " + this.getImagePath());
        System.out.println("Mana: " + this.getMana());
        System.out.println("Duration: " + this.getDuration());
    }

    public boolean isInactive() {
        // Boolean to check if the card is already inactive
        return this.getStatus() == Status.INACTIVE;
    }
    
    public Status decreaseDuration() {
        // Count lifetime of spell
        // Called every turns
        // On first round, no need to decrease duration, just change status to SECONDROUND
        if (this.status == Status.FIRSTROUND) {
            if(this.duration == 0){
                this.status = Status.PERM;
            }
            else{
                this.status = Status.TEMP;
                this.duration--;
                if(this.duration == 0){
                    this.status = Status.INACTIVE;
                }
            }
            
            System.out.println("FIRSTROUND jadi pindah SECONDROUND");
        }
        else if (this.status == Status.TEMP) {
            // On second round after spell is casted, it is set to active
            this.duration--;
            if(this.duration ==0){
                this.status = Status.INACTIVE;
            }
        }
        
        System.out.println("DURATION " + this.duration);
        return this.status;
    }    

    abstract ArrayList<Object> giveEffect();
}