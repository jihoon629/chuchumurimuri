package com.counter.calculator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EntityEq {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    Double skillSpeedSet;
    @Column
    Double atackSpeedSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_skill_id", referencedColumnName = "id")
    public EntityEqSubSkill subSkill;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_attack_id", referencedColumnName = "id")
    public EntityEqSubAttack subAttack;

    public EntityEq() {
    }

    public EntityEq(Double skillSpeedSet, Double atackSpeedSet) {
        super();
        this.skillSpeedSet = skillSpeedSet;
        this.atackSpeedSet = atackSpeedSet;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSkillSpeedSet() {
        return skillSpeedSet;
    }

    public void setSkillSpeedSet(Double skillSpeedSet) {
        this.skillSpeedSet = skillSpeedSet;
    }

    public Double getAtackSpeedSet() {
        return atackSpeedSet;
    }

    public void setAtackSpeedSet(Double atackSpeedSet) {
        this.atackSpeedSet = atackSpeedSet;
    }

    public EntityEqSubSkill getSubSkill() {
        return subSkill;
    }

    public void setSubSkill(EntityEqSubSkill subSkill) {
        this.subSkill = subSkill;
    }

    public EntityEqSubAttack getSubAttack() {
        return subAttack;
    }

    public void setSubAttack(EntityEqSubAttack subAttack) {
        this.subAttack = subAttack;
    }

    @Override
    public String toString() {
        return "EntityEq [id=" + id + ", skillSpeedSet=" + skillSpeedSet + ", atackSpeedSet=" + atackSpeedSet
                + ", subSkill=" + subSkill + ", subAttack=" + subAttack + "]";
    }

    public EntityEq toEntityEq() {
        return new EntityEq();
    }

public EntityEqSubSkill toEntityEqSubSkill(){
return new EntityEqSubSkill();

}

public EntityEqSubAttack toEntityEqSubAttack(){
    return new EntityEqSubAttack();
}
}