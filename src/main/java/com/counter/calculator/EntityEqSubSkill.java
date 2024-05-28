package com.counter.calculator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EntityEqSubSkill {
    
 @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    Double skillSpeedHands;
    @Column
    Double skillSpeedCase;
    @Column
    Double skillSpeedMovement;
    @Column
    Double skillSpeedGear;

    public EntityEqSubSkill() {
    }

    public EntityEqSubSkill(Double skillSpeedHands, Double skillSpeedCase, Double atackSpeedMovement, Double skillSpeedGear) {
        super();
        this.skillSpeedHands = skillSpeedHands;
        this.skillSpeedCase = skillSpeedCase;
        this.skillSpeedMovement = atackSpeedMovement;
        this.skillSpeedGear = skillSpeedGear;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSkillSpeedHands() {
        return skillSpeedHands;
    }

    public void setSkillSpeedHands(Double skillSpeedHands) {
        this.skillSpeedHands = skillSpeedHands;
    }

    public Double getSkillSpeedCase() {
        return skillSpeedCase;
    }

    public void setSkillSpeedCase(Double skillSpeedCase) {
        this.skillSpeedCase = skillSpeedCase;
    }

    public Double getSkillSpeedMovement() {
        return skillSpeedMovement;
    }

    public void setSkillSpeedMovement(Double atackSpeedMovement) {
        this.skillSpeedMovement = atackSpeedMovement;
    }

    public Double getSkillSpeedGear() {
        return skillSpeedGear;
    }

    public void setSkillSpeedGear(Double skillSpeedGear) {
        this.skillSpeedGear = skillSpeedGear;
    }

 

    
}
