package com.counter.calculator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EntityEqSubAttack {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;


    @Column
    private Double atackSpeedHands;
    @Column
    private  Double atackSpeedCase;
    @Column
    private  Double atackSpeedMovement;
    @Column
    private  Double atackSpeedGear;

    public EntityEqSubAttack() {
    }

    public EntityEqSubAttack(Double atackSpeedHands, Double atackSpeedCase, Double skillSpeedMovement, Double atackSpeedGear) {
        super();
        this.atackSpeedHands = atackSpeedHands;
        this.atackSpeedCase = atackSpeedCase;
        this.atackSpeedMovement = skillSpeedMovement;
        this.atackSpeedGear = atackSpeedGear;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAtackSpeedHands() {
        return atackSpeedHands;
    }

    public void setAtackSpeedHands(Double atackSpeedHands) {
        this.atackSpeedHands = atackSpeedHands;
    }

    public Double getAtackSpeedCase() {
        return atackSpeedCase;
    }

    public void setAtackSpeedCase(Double atackSpeedCase) {
        this.atackSpeedCase = atackSpeedCase;
    }

    public Double getAtackSpeedMovement() {
        return atackSpeedMovement;
    }

    public void setAtackSpeedMovement(Double skillSpeedMovement) {
        this.atackSpeedMovement = skillSpeedMovement;
    }

    public Double getAtackSpeedGear() {
        return atackSpeedGear;
    }

    public void setAtackSpeedGear(Double atackSpeedGear) {
        this.atackSpeedGear = atackSpeedGear;
    }




    
}
