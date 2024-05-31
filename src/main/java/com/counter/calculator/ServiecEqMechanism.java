package com.counter.calculator;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ServiecEqMechanism {

    public double nomalAttackResult(List<EntitySkill> charaInfo, double eqResult, double extraAttackSpeed) {
        double nomalAttackResult = charaInfo.get(0).getNomalAttack() / (1 + ((extraAttackSpeed+eqResult )* 0.01));
        return nomalAttackResult;
    }

    public double passiveskillResult(List<EntitySkill> charaInfo, double nomalAttackResult) {
        if (charaInfo.get(0).getPassiveskill() == 0) {
            double passiveskillResult = 0;
            return passiveskillResult;
        } else {
            double passiveskillResult = charaInfo.get(0).getPassiveskill() * nomalAttackResult;
            return passiveskillResult;
        }
    }

    public double activeSkillResult(List<EntitySkill> charaInfo, double eqResult) {
        double activeSkillResult = charaInfo.get(0).getActiveskill() / (1 + (eqResult * 0.01));
        return activeSkillResult;
    }

    public double ultSkillResult(List<EntitySkill> charaInfo, double eqResult) {
        double ultSkillResult=charaInfo.get(0).getUltSkill() / (1 + (eqResult * 0.01));
       
        return  ultSkillResult; 
    }

    public double activeSkillResultFury(List<EntitySkill> charaInfo, double nomalAttackResult) {
        double activeSkillResultFury=charaInfo.get(0).getActiveskill() * nomalAttackResult;
        return  activeSkillResultFury;
    }

    public double ultSkillResultFury(List<EntitySkill> charaInfo, double nomalAttackResult) {
        double ultSkillResultFury= charaInfo.get(0).getUltSkill() * nomalAttackResult;
        return ultSkillResultFury;
    }

    public double[] eqResult(List<EntityEq> eqInfo,double extraSkillSpeed) {
        double eqResultSkill = eqInfo.get(0).getSkillSpeedSet() + eqInfo.get(0).getSubSkill().getSkillSpeedHands()
                + eqInfo.get(0).getSubSkill().getSkillSpeedCase() + eqInfo.get(0).getSubSkill().getSkillSpeedGear()
                + eqInfo.get(0).getSubSkill().getSkillSpeedMovement()+extraSkillSpeed;
        double eqResultAttack = eqInfo.get(0).getAtackSpeedSet() + eqInfo.get(0).getSubAttack().getAtackSpeedHands()
                + eqInfo.get(0).getSubAttack().getAtackSpeedCase() + eqInfo.get(0).getSubAttack().getAtackSpeedGear()
                + eqInfo.get(0).getSubAttack().getAtackSpeedMovement();
        return new double[] { eqResultSkill, eqResultAttack };
    }


}
