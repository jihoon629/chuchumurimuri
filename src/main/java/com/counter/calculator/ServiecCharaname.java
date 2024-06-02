package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiecCharaname {
    
     @Autowired
     private ServiecEqMechanism eqMechanism;


   
     public double[] YuMina(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double durationSeconds) {
        double extraAttackSpeed = 20;
        double extraSkillSpeed = 0;

        double[] cooldowns = calculateCooldowns(charaInfo, eqInfo, extraSkillSpeed, extraAttackSpeed);
        double normalAttackSpeed = cooldowns[0];
        double passiveSkillCooldown = cooldowns[1];
        double activeSkillCooldown = cooldowns[2];
        double ultSkillCooldown = cooldowns[3];

        double time = 0;
        int normalAttacks = 0;
        int passiveSkillUses = 0;
        int activeSkillUses = 1; // 출격 직후 1회 사용
        int ultSkillUses = 0;

        double nextNormalAttackTime = normalAttackSpeed;
        double nextPassiveSkillTime = passiveSkillCooldown; // Assuming passive skill is on cooldown from the start
        double nextActiveSkillTime = activeSkillCooldown; // 처음 특수기 사용 후 쿨타임
        double nextUltSkillTime = ultSkillCooldown; // Assuming ult skill is on cooldown from the start

        boolean attackSpeedBuffActive = true;
        double attackSpeedBuffEndTime = 4; // 출격 후 4초간 공격속도 버프
        double attackSpeedBuffMultiplier = 1.3;

        while (time < durationSeconds) {
            // Determine the next action based on the earliest cooldown
            double nextActionTime = Math.min(nextNormalAttackTime, Math.min(nextPassiveSkillTime, Math.min(nextActiveSkillTime, nextUltSkillTime)));

            // Advance time to the next action time
            time = nextActionTime;

            // Check if attack speed buff has ended
            if (attackSpeedBuffActive && time >= attackSpeedBuffEndTime) {
                attackSpeedBuffActive = false;
                normalAttackSpeed *= attackSpeedBuffMultiplier; // 버프가 끝나면 원래 속도로
                nextNormalAttackTime = time + normalAttackSpeed;
            }

            if (time >= nextNormalAttackTime) {
                normalAttacks++;
                nextNormalAttackTime += normalAttackSpeed;
            }
            if (time >= nextPassiveSkillTime) {
                passiveSkillUses++;
                nextPassiveSkillTime += passiveSkillCooldown;

                // Activate attack speed buff for 4 seconds
                attackSpeedBuffActive = true;
                attackSpeedBuffEndTime = time + 4;
                normalAttackSpeed /= attackSpeedBuffMultiplier; // 버프 동안 속도 증가
                nextNormalAttackTime = time + normalAttackSpeed;

                // Reduce ultimate skill cooldown by 3 seconds
                nextUltSkillTime = Math.max(nextUltSkillTime - 3, time);
            }
            if (time >= nextActiveSkillTime) {
                activeSkillUses++;
                nextActiveSkillTime += activeSkillCooldown;

                // Activate passive skill
                passiveSkillUses++;
                nextPassiveSkillTime += passiveSkillCooldown;

                // Activate attack speed buff for 4 seconds
                attackSpeedBuffActive = true;
                attackSpeedBuffEndTime = time + 4;
                normalAttackSpeed /= attackSpeedBuffMultiplier; // 버프 동안 속도 증가
                nextNormalAttackTime = time + normalAttackSpeed;

                // Reduce ultimate skill cooldown by 3 seconds
                nextUltSkillTime = Math.max(nextUltSkillTime - 3, time);
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
double[] eqResults = eqMechanism.eqResult(eqInfo, extraSkillSpeed);
double eqResultSkill = eqResults[0];
double eqResultAttack = eqResults[1];
double normalAttackSpeed = eqMechanism.nomalAttackResult(charaInfo, eqResultAttack, extraAttackSpeed); // 최종 공격
                                                                                                   // 속도
double passiveSkillCooldown = eqMechanism.passiveskillResult(charaInfo, normalAttackSpeed); // 캐릭터 패시브 쿨타임
double activeSkillCooldown = eqMechanism.activeSkillResult(charaInfo, eqResultSkill); // 캐릭터 특수기 쿨타임
double ultSkillCooldown = eqMechanism.ultSkillResult(charaInfo, eqResultSkill); // 캐릭터 궁극기 쿨타임
return new double[] { normalAttackSpeed, passiveSkillCooldown, activeSkillCooldown, ultSkillCooldown };
}

}
