package com.counter.calculator;

public class DtoChangeEq {
    public long userid;
    public Double skillSpeedSet = 0.0;
    public Double atackSpeedSet = 0.0;
    public Double skillSpeedHands = 0.0;
    public Double atackSpeedHands = 0.0;
    public Double skillSpeedCase = 0.0;
    public Double atackSpeedCase = 0.0;
    public Double skillSpeedMovement = 0.0;
    public Double atackSpeedMovement = 0.0;
    public Double skillSpeedGear = 0.0;
    public Double atackSpeedGear = 0.0;

    public DtoChangeEq() {

    }

    public DtoChangeEq(long userid, Double skillSpeedSet, Double atackSpeedSet, Double skillSpeedHands,
            Double atackSpeedHands,
            Double skillSpeedCase, Double atackSpeedCase, Double skillSpeedMovement, Double atackSpeedMovement,
            Double skillSpeedGear, Double atackSpeedGear) {
        this.userid = userid;
        this.skillSpeedSet = skillSpeedSet;
        this.atackSpeedSet = atackSpeedSet;
        this.skillSpeedHands = skillSpeedHands;
        this.atackSpeedHands = atackSpeedHands;
        this.skillSpeedCase = skillSpeedCase;
        this.atackSpeedCase = atackSpeedCase;
        this.skillSpeedMovement = skillSpeedMovement;
        this.atackSpeedMovement = atackSpeedMovement;
        this.skillSpeedGear = skillSpeedGear;
        this.atackSpeedGear = atackSpeedGear;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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

    public Double getSkillSpeedHands() {
        return skillSpeedHands;
    }

    public void setSkillSpeedHands(Double skillSpeedHands) {
        this.skillSpeedHands = skillSpeedHands;
    }

    public Double getAtackSpeedHands() {
        return atackSpeedHands;
    }

    public void setAtackSpeedHands(Double atackSpeedHands) {
        this.atackSpeedHands = atackSpeedHands;
    }

    public Double getSkillSpeedCase() {
        return skillSpeedCase;
    }

    public void setSkillSpeedCase(Double skillSpeedCase) {
        this.skillSpeedCase = skillSpeedCase;
    }

    public Double getAtackSpeedCase() {
        return atackSpeedCase;
    }

    public void setAtackSpeedCase(Double atackSpeedCase) {
        this.atackSpeedCase = atackSpeedCase;
    }

    public Double getSkillSpeedMovement() {
        return skillSpeedMovement;
    }

    public void setSkillSpeedMovement(Double skillSpeedMovement) {
        this.skillSpeedMovement = skillSpeedMovement;
    }

    public Double getAtackSpeedMovement() {
        return atackSpeedMovement;
    }

    public void setAtackSpeedMovement(Double atackSpeedMovement) {
        this.atackSpeedMovement = atackSpeedMovement;
    }

    public Double getSkillSpeedGear() {
        return skillSpeedGear;
    }

    public void setSkillSpeedGear(Double skillSpeedGear) {
        this.skillSpeedGear = skillSpeedGear;
    }

    public Double getAtackSpeedGear() {
        return atackSpeedGear;
    }

    public void setAtackSpeedGear(Double atackSpeedGear) {
        this.atackSpeedGear = atackSpeedGear;
    }

    public EntityEq toEntityEq() {
        EntityEq entityEq = new EntityEq();
        entityEq.setSkillSpeedSet(this.skillSpeedSet);
        entityEq.setAtackSpeedSet(this.atackSpeedSet);
        entityEq.setUserid(this.userid);
        entityEq.setSubSkill(this.toSubSkillEntity());
        entityEq.setSubAttack(this.toSubAttackEntity());
        return entityEq;
    }

    private EntityEqSubAttack toSubAttackEntity() {
        return new EntityEqSubAttack(
                this.atackSpeedHands,
                this.atackSpeedCase,
                this.skillSpeedMovement,
                this.atackSpeedGear);
    }

    private EntityEqSubSkill toSubSkillEntity() {
        return new EntityEqSubSkill(
                this.skillSpeedHands,
                this.skillSpeedCase,
                this.skillSpeedMovement,
                this.skillSpeedGear);

    }

}