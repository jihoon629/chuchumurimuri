package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiecCharaMecanism {
    
    @Autowired
    ServiecEqMechanism eqMechanism;


    public void calculateSkillUsage(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double durationSeconds) {
       
        double extraAttackSpeed=0;
       double extraSkillSpeed=0;
       
       double[] cooldowns = calculateCooldowns(charaInfo, eqInfo, extraSkillSpeed, extraAttackSpeed);
       double normalAttackSpeed = cooldowns[0];
       double passiveSkillCooldown = cooldowns[1];
       double activeSkillCooldown = cooldowns[2];
       double ultSkillCooldown = cooldowns[3];
       
        double normalAttackCount = durationSeconds / normalAttackSpeed;
        double passiveSkillCount = passiveSkillCooldown > 0 ? durationSeconds / passiveSkillCooldown : 0;
        double activeSkillCount = (durationSeconds - activeSkillCooldown) / activeSkillCooldown + 1; 
        double ultSkillCount = durationSeconds / ultSkillCooldown;

        System.out.println(charaInfo.get(0).getCharaName() + "의 스킬 사용 횟수");
        System.out.println("평타 시간 : " +normalAttackSpeed);
        System.out.println(" 패시브 시간 : " + passiveSkillCooldown);
        System.out.println(" 특수기 시간: " +activeSkillCooldown);
        System.out.println("궁극기 시간: " + ultSkillCooldown);


    System.out.println("30초 동안 평타 사용 횟수: " + (int) normalAttackCount);
    System.out.println("30초 동안 패시브 사용 횟수: " + (int) passiveSkillCount);
    System.out.println("30초 동안 특수기 사용 횟수: " + (int) activeSkillCount);
    System.out.println("30초 동안 궁극기 사용 횟수: " + (int) ultSkillCount);
    }


    public double[] calculateCooldowns(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double extraSkillSpeed, double extraAttackSpeed) {
        double[] eqResults = eqMechanism.eqResult(eqInfo,extraSkillSpeed);
        double eqResultSkill = eqResults[0];
        double eqResultAttack = eqResults[1];
        double normalAttackSpeed = eqMechanism.nomalAttackResult(charaInfo, eqResultAttack,extraAttackSpeed); // 최종 공격 속도
        double passiveSkillCooldown = eqMechanism.passiveskillResult(charaInfo, normalAttackSpeed); // 캐릭터 패시브 쿨타임
        double activeSkillCooldown = eqMechanism.activeSkillResult(charaInfo, eqResultSkill); // 캐릭터 특수기 쿨타임
        double ultSkillCooldown = eqMechanism.ultSkillResult(charaInfo, eqResultSkill); // 캐릭터 궁극기 쿨타임
        return new double[] {normalAttackSpeed, passiveSkillCooldown, activeSkillCooldown, ultSkillCooldown};
    }




}








// double[] eqResults = eqMechanism.eqResult(eqInfo,extraSkillSpeed);
// double eqResultSkill = eqResults[0];
// double eqResultAttack = eqResults[1];
// double normalAttackSpeed = eqMechanism.nomalAttackResult(charaInfo, eqResultAttack,extraAttackSpeed); // 최종 공격 속도
// double passiveSkillCooldown = eqMechanism.passiveskillResult(charaInfo, normalAttackSpeed); // 캐릭터 패시브 쿨타임
// double activeSkillCooldown = eqMechanism.activeSkillResult(charaInfo, eqResultSkill); // 캐릭터 특수기 쿨타임
// double ultSkillCooldown = eqMechanism.ultSkillResult(charaInfo, eqResultSkill); // 캐릭터 궁극기 쿨타임


    // public void calculateResults(List<EntitySkill> charaInfo, List<EntityEq> eqInfo) {
    //     double[] eqResults = eqMechanism.eqResult(eqInfo);
    //     double eqResultSkill = eqResults[0];// 총 스킬충전 속도
    //     double eqResultAttack = eqResults[1];// 총 공격 속도


    //     double normalAttack = eqMechanism.nomalAttackResult(charaInfo, eqResultAttack); // 최종 공격 속도
    //     double passiveSkill = eqMechanism.passiveskillResult(charaInfo, eqResultAttack, normalAttack);// 캐릭터 패시브 쿨타임
    //     double activeSkill = eqMechanism.activeSkillResult(charaInfo, eqResultSkill);// 캐릭터 특수기 쿨타임
    //     double ultSkill = eqMechanism.ultSkillResult(charaInfo, eqResultSkill);// 캐릭터 궁극기 쿨타임
     
    //     System.out.println("일반 공격 결과: " + normalAttack);
    //     System.out.println("패시브 스킬 결과: " + passiveSkill);
    //     System.out.println("특수기 스킬 결과: " + activeSkill);
    //     System.out.println("궁극기 스킬 결과: " + ultSkill);
    // }



