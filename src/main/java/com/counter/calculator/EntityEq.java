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
    private int id;

    @Column
    private long userid;

    @Column
    private Double skillSpeedSet;
    @Column
    private Double atackSpeedSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_skill_id", referencedColumnName = "id")
    private EntityEqSubSkill subSkill;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_attack_id", referencedColumnName = "id")
    private EntityEqSubAttack subAttack;

    public EntityEq() {
    }

    public EntityEq(long userid,Double skillSpeedSet, Double atackSpeedSet) {
        super();
        this.userid = userid;
        this.skillSpeedSet = skillSpeedSet;
        this.atackSpeedSet = atackSpeedSet;

    }
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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
        return "EntityEq [id=" + id + ", userid=" + userid + ", skillSpeedSet=" + skillSpeedSet + ", atackSpeedSet="
                + atackSpeedSet + ", subSkill=" + subSkill + ", subAttack=" + subAttack + "]";
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