package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class Serviec {
    @Autowired
    Repo_Skill_Info skillRepo;

    @Autowired
    Repo_Eq_info eqRepo;

    public void join(DtoSelect sel, Model model) {
        skillRepo.save(sel.toEntity());

        List<EntitySkill> charaInfo = skillRepo.siibal();
        if (!charaInfo.isEmpty()) {
            model.addAttribute("charanum", charaInfo.get(0).getCharaNum());
        } else {
            model.addAttribute("charanum", "데이터 없음");
        }
    }

    public void change(DtoChangeEq chageEq) {
        eqRepo.save(chageEq.toEntityEq());
    }

    public void cal(DtoChangeEq chageEq, DtoSelect sel, Model model) {
        List<EntitySkill> charaInfo = skillRepo.siibal();
        List<EntityEq> eqInfo = eqRepo.siiballlll();

        if (charaInfo.isEmpty() || eqInfo.isEmpty()) {
            model.addAttribute("error", "캐릭터 또는 장비 정보가 없습니다.");
            return;
        }

        double nomalAttackResult = nomalAttackResult(charaInfo, eqInfo);
        double passiveskillResult = passiveskillResult(charaInfo, eqInfo, nomalAttackResult);
        double activeSkillResult;
        double ultSkillResult;
        if (charaInfo.get(0).getCharaNum() < 98) {
            activeSkillResult = activeSkillResult(charaInfo, eqInfo);
            ultSkillResult = ultSkillResult(charaInfo, eqInfo);
        } else {
            activeSkillResult = activeSkillResultFury(charaInfo, eqInfo, nomalAttackResult);
            ultSkillResult = ultSkillResultFury(charaInfo, eqInfo, nomalAttackResult);
        }

        model.addAttribute("nomalAttact", Math.round(charaInfo.get(0).getNomalAttack() * 10) / 10.0);
        model.addAttribute("passiveSkill", Math.round(charaInfo.get(0).getPassiveskill() * charaInfo.get(0).getNomalAttack() * 10) / 10.0);
        model.addAttribute("activeSkill", charaInfo.get(0).getActiveskill());
        model.addAttribute("ultSkill", charaInfo.get(0).getUltSkill());
        model.addAttribute("nomalAttackResult", Math.round(nomalAttackResult * 10) / 10.0);
        model.addAttribute("passiveSkillResult", Math.round(passiveskillResult * 10) / 10.0);
        model.addAttribute("activeSkillResult", Math.round(activeSkillResult * 10) / 10.0);
        model.addAttribute("ultSkillResult", Math.round(ultSkillResult * 10) / 10.0);
        model.addAttribute("charanum", charaInfo.get(0).getCharaNum());
    }

    public void restart(DtoSelect sel, DtoChangeEq changeEq) {
        skillRepo.deleteAllDataSkill();
        eqRepo.deleteAllDataSkillEq();
    }

    public double nomalAttackResult(List<EntitySkill> charaInfo, List<EntityEq> eqInfo) {
        return charaInfo.get(0).getNomalAttack() / (1 + (eqInfo.get(0).getAtackSpeedSet() * 0.01));
    }

    public double passiveskillResult(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double nomalAttackResult) {
        if (charaInfo.get(0).getPassiveskill() == 0) {
            return 0;
        }
        return charaInfo.get(0).getPassiveskill() * nomalAttackResult;
    }

    public double activeSkillResult(List<EntitySkill> charaInfo, List<EntityEq> eqInfo) {
        return charaInfo.get(0).getActiveskill() / (1 + (eqInfo.get(0).getSkillSpeedSet() * 0.01));
    }

    public double ultSkillResult(List<EntitySkill> charaInfo, List<EntityEq> eqInfo) {
        return charaInfo.get(0).getUltSkill() / (1 + (eqInfo.get(0).getSkillSpeedSet() * 0.01));
    }

    public double activeSkillResultFury(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double nomalAttackResult) {
        return charaInfo.get(0).getActiveskill() * nomalAttackResult;
    }

    public double ultSkillResultFury(List<EntitySkill> charaInfo, List<EntityEq> eqInfo, double nomalAttackResult) {
        return charaInfo.get(0).getUltSkill() * nomalAttackResult;
    }
}

//     public double eqResult(List<EntityEq> eqInfo, List<EntityEqSubSkill> eqSubSkill) {
//         double eqResultSkillSpeed = eqInfo.get(0).getSkillSpeedSet()+eqSubSkill.get(0).getSkillSpeedHands();
//         return eqResultSkillSpeed;
// }

