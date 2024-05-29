package com.counter.calculator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EntitySkill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    long userid;

    @Column
    int charaNum;
    @Column
    String charaName;
    @Column
    double nomalAttack;
    @Column
    int passiveskill;
    @Column
    int activeskill;
    @Column
    int ultSkill;


    public EntitySkill() {

    }

public EntitySkill(long userid,int charaNum, String charaName, double nomalAttack, int passiveskill, int activeskill, int ultSkill) {
    super();
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


public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
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

public EntitySkill toEntity() {
    return new EntitySkill();
}

@Override
public String toString() {
    return "EntitySkill [id=" + id + ", userid=" + userid + ", charaNum=" + charaNum + ", charaName=" + charaName
            + ", nomalAttack=" + nomalAttack + ", passiveskill=" + passiveskill + ", activeskill=" + activeskill
            + ", ultSkill=" + ultSkill + "]";
}






}
