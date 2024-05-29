package com.counter.calculator;

public class DtoSelect {

    public long userid;
    public int charaNum;
    public String charaName;
    public double nomalAttack;
    public int passiveskill;
    public int activeskill;
    public int ultSkill;

    public DtoSelect() {

    }

    public DtoSelect(long userid, int charaNum, String charaName, double nomalAttack, int passiveskill, int activeskill,
            int ultSkill) {
        this.userid = userid;
        this.charaNum = charaNum;
        this.charaName = charaName;
        this.nomalAttack = nomalAttack;
        this.passiveskill = passiveskill;
        this.activeskill = activeskill;
        this.ultSkill = ultSkill;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {

        this.userid = userid;
    }

    public int getCharaNum() {
        return charaNum;
    }

    public void setCharaNum(int charaNum) {
        this.charaNum = charaNum;
    }

    public String getCharaName() {
        return charaName;
    }

    public void setCharaName(String charaName) {
        this.charaName = charaName;
    }

    public double getNomalAttack() {
        return nomalAttack;
    }

    public void setNomalAttack(double nomalAttack) {
        this.nomalAttack = nomalAttack;
    }

    public int getPassiveskill() {
        return passiveskill;
    }

    public void setPassiveskill(int passiveskill) {
        this.passiveskill = passiveskill;
    }

    public int getActiveskill() {
        return activeskill;
    }

    public void setActiveskill(int activeskill) {
        this.activeskill = activeskill;
    }

    public int getUltSkill() {
        return ultSkill;
    }

    public void setUltSkill(int ultSkill) {
        this.ultSkill = ultSkill;
    }

    @Override
    public String toString() {
        return "DtoSelect [charaNum=" + charaNum + ", CharaName=" + charaName + ", NomalAttack=" + nomalAttack
                + ", Passiveskill=" + passiveskill + ", Activeskill=" + activeskill + ", UltSkill=" + ultSkill + "]";
    }

    public EntitySkill toEntity() {

        return new EntitySkill(
                this.userid,
                this.charaNum,
                this.charaName,
                this.nomalAttack,
                this.passiveskill,
                this.activeskill,
                this.ultSkill);

    }

}