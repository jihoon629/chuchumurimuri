package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiecCharaname {
    
     @Autowired
     private ServiecEqMechanism eqMechanism;

    public double[] Default(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double durationSeconds) {
        double extraAttackSpeed = 0;
        double extraSkillSpeed = 0;
    
        double[] cooldowns = calculateCooldowns(charaInfo, eqInfo, extraSkillSpeed, extraAttackSpeed);
        double normalAttackSpeed = cooldowns[0];
        double passiveSkillCooldown = cooldowns[1];
        double activeSkillCooldown = cooldowns[2];
        double ultSkillCooldown = cooldowns[3];
        double eqResultSkill = cooldowns[4];
        double eqResultAttack = cooldowns[5];

    
        int normalAttacks = 0;
        int passiveSkillUses = 0;
        int activeSkillUses = 0;
        int ultSkillUses = 0;
    
        double time = 0;
        double nextNormalAttackTime = normalAttackSpeed;
        double nextPassiveSkillTime = (passiveSkillCooldown > 0) ? passiveSkillCooldown : Double.MAX_VALUE;
        double nextActiveSkillTime = activeSkillCooldown;
        double nextUltSkillTime = ultSkillCooldown;
    
        while (time < durationSeconds) {
            // Determine the next action based on the earliest cooldown
            double nextActionTime = Math.min(nextNormalAttackTime, Math.min(nextPassiveSkillTime, Math.min(nextActiveSkillTime, nextUltSkillTime)));
    
            // Advance time to the next action time
            time = nextActionTime;
    
            if (time >= nextNormalAttackTime) {
                normalAttacks++;
                nextNormalAttackTime += normalAttackSpeed;
            }
            if (time >= nextPassiveSkillTime && passiveSkillCooldown > 0) {
                passiveSkillUses++;
                nextPassiveSkillTime += passiveSkillCooldown;
            }
            if (time >= nextActiveSkillTime) {
                activeSkillUses++;
                nextActiveSkillTime += activeSkillCooldown;
            }
            if (time >= nextUltSkillTime) {
                ultSkillUses++;
                nextUltSkillTime += ultSkillCooldown;
            }
        }
    
        return new double[] { normalAttacks, passiveSkillUses, activeSkillUses, ultSkillUses };
    }
   

    public double[] Fury(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double durationSeconds){
        double extraAttackSpeed = 0;
        double extraSkillSpeed = 0;
    
        double[] cooldowns = calculateCooldowns(charaInfo, eqInfo, extraSkillSpeed, extraAttackSpeed);
        double normalAttackSpeed = cooldowns[0];
        double passiveSkillCooldown = cooldowns[1];
        double activeSkillCooldown = cooldowns[6];
        double ultSkillCooldown = cooldowns[7];
        double eqResultSkill = cooldowns[4];
        double eqResultAttack = cooldowns[5];

    
        int normalAttacks = 0;
        int passiveSkillUses = 0;
        int activeSkillUses = 0;
        int ultSkillUses = 0;
    
        double time = 0;
        double nextNormalAttackTime = normalAttackSpeed;
        double nextPassiveSkillTime = (passiveSkillCooldown > 0) ? passiveSkillCooldown : Double.MAX_VALUE;
        double nextActiveSkillTime = activeSkillCooldown;
        double nextUltSkillTime = ultSkillCooldown;
    
        while (time < durationSeconds) {
            // Determine the next action based on the earliest cooldown
            double nextActionTime = Math.min(nextNormalAttackTime, Math.min(nextPassiveSkillTime, Math.min(nextActiveSkillTime, nextUltSkillTime)));
    
            // Advance time to the next action time
            time = nextActionTime;
    
            if (time >= nextNormalAttackTime) {
                normalAttacks++;
                nextNormalAttackTime += normalAttackSpeed;
            }
            if (time >= nextPassiveSkillTime && passiveSkillCooldown > 0) {
                passiveSkillUses++;
                nextPassiveSkillTime += passiveSkillCooldown;
            }
            if (time >= nextActiveSkillTime) {
                activeSkillUses++;
                nextActiveSkillTime += activeSkillCooldown;
            }
            if (time >= nextUltSkillTime) {
                ultSkillUses++;
                nextUltSkillTime += ultSkillCooldown;
            }
        }
    
        return new double[] { normalAttacks, passiveSkillUses, activeSkillUses, ultSkillUses };
    


    }
    
    
    

private double[] calculateCooldowns(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double extraSkillSpeed,
double extraAttackSpeed) {
double[] eqResults = eqMechanism.eqResult(eqInfo, extraSkillSpeed, extraAttackSpeed);
double eqResultSkill = eqResults[0];
double eqResultAttack = eqResults[1];

double normalAttackSpeed = eqMechanism.nomalAttackResult(charaInfo, eqResultAttack, extraAttackSpeed); // 최종 공격
                                                                                                   // 속도
double passiveSkillCooldown = eqMechanism.passiveskillResult(charaInfo, normalAttackSpeed); // 캐릭터 패시브 쿨타임
double activeSkillCooldown = eqMechanism.activeSkillResult(charaInfo, eqResultSkill); // 캐릭터 특수기 쿨타임
double ultSkillCooldown = eqMechanism.ultSkillResult(charaInfo, eqResultSkill); // 캐릭터 궁극기 쿨타임

double  activeskillFury=eqMechanism.activeSkillResultFury(charaInfo, normalAttackSpeed);
double  ultskillFury=eqMechanism.ultSkillResultFury(charaInfo, normalAttackSpeed);
return new double[] { normalAttackSpeed, passiveSkillCooldown, activeSkillCooldown, ultSkillCooldown,eqResultSkill, eqResultAttack,activeskillFury,ultskillFury };
}

}
