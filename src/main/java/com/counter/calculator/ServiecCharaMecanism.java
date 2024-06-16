package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiecCharaMecanism {

    @Autowired
    private ServiecCharaname charaName;

    public double[] calculateDealTime(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double durationSeconds) {
        double normalAttackCount = 0;
        double passiveSkillCount = 0;
        double activeSkillCount = 0;
        double ultSkillCount = 0;

        if (charaInfo.get(0).getCharaNum() < 96){
            double[] charaResults = charaName.Default(charaInfo, eqInfo, durationSeconds);
            normalAttackCount = charaResults[0];
            passiveSkillCount = charaResults[1];
            activeSkillCount = charaResults[2];
            ultSkillCount = charaResults[3];
            System.out.println(charaInfo.get(0).getCharaNum());
            return new double[] { (int) normalAttackCount, (int) passiveSkillCount, (int) activeSkillCount,
                (int) ultSkillCount };
            } else {
                double[] charaResults = charaName.Fury(charaInfo, eqInfo, durationSeconds);
                normalAttackCount = charaResults[0];
                passiveSkillCount = charaResults[1];
                activeSkillCount = charaResults[2];
                ultSkillCount = charaResults[3];
                System.out.println("Fury");
                

        return new double[] { (int) normalAttackCount, (int) passiveSkillCount, (int) activeSkillCount,
                (int) ultSkillCount };
    }

}
}