package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class Serviec {
    @Autowired
    Repo_Skill_Info skillRepo;

    @Autowired
    Repo_Eq_info eqRepo;

    public void join(DtoSelect sel, Model model) {
        long userId = sel.getUserid();
        skillRepo.save(sel.toEntity());
        List<EntitySkill> charaInfo = skillRepo.findByuserid(userId);
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
        List<EntitySkill> charaInfo = skillRepo.findByuserid(sel.getUserid());
        List<EntityEq> eqInfo = eqRepo.findByuserid(chageEq.getUserid());

        if (charaInfo.isEmpty() || eqInfo.isEmpty()) {
            System.out.println("charaInfo or eqInfo is empty");
            return;
        }

        double[] eqResult = eqResult(eqInfo);

        double nomalAttackResult = nomalAttackResult(charaInfo, eqResult[1]);
        double passiveskillResult = passiveskillResult(charaInfo, eqResult[1], nomalAttackResult);

        double activeSkillResult;
        double ultSkillResult;
        if (charaInfo.get(0).getCharaNum() < 98) {
            activeSkillResult = activeSkillResult(charaInfo, eqResult[0]);
            ultSkillResult = ultSkillResult(charaInfo, eqResult[0]);
        } else {
            activeSkillResult = activeSkillResultFury(charaInfo, nomalAttackResult);
            ultSkillResult = ultSkillResultFury(charaInfo, nomalAttackResult);
        }

        model.addAttribute("nomalAttact", Math.round(charaInfo.get(0).getNomalAttack() * 10) / 10.0);
        model.addAttribute("passiveSkill",
                Math.round(charaInfo.get(0).getPassiveskill() * charaInfo.get(0).getNomalAttack() * 10) / 10.0);
        model.addAttribute("activeSkill", charaInfo.get(0).getActiveskill());
        model.addAttribute("ultSkill", charaInfo.get(0).getUltSkill());
        model.addAttribute("nomalAttackResult", Math.round(nomalAttackResult * 10) / 10.0);
        model.addAttribute("passiveSkillResult", Math.round(passiveskillResult * 10) / 10.0);
        model.addAttribute("activeSkillResult", Math.round(activeSkillResult * 10) / 10.0);
        model.addAttribute("ultSkillResult", Math.round(ultSkillResult * 10) / 10.0);
        model.addAttribute("charanum", charaInfo.get(0).getCharaNum());
    }



    @Transactional
    public void deleteDataByUserId(Long userId) {
        eqRepo.deleteByUserid(userId);
        skillRepo.deleteByUserid(userId);
    }

    public double nomalAttackResult(List<EntitySkill> charaInfo, double eqResult) {
        return charaInfo.get(0).getNomalAttack() / (1 + (eqResult * 0.01));
    }

    public double passiveskillResult(List<EntitySkill> charaInfo, double eqResult, double nomalAttackResult) {
        if (charaInfo.get(0).getPassiveskill() == 0) {
            return 0;
        }
        return charaInfo.get(0).getPassiveskill() * nomalAttackResult;
    }

    public double activeSkillResult(List<EntitySkill> charaInfo, double eqResult) {
        return charaInfo.get(0).getActiveskill() / (1 + (eqResult * 0.01));
    }

    public double ultSkillResult(List<EntitySkill> charaInfo, double eqResult) {
        return charaInfo.get(0).getUltSkill() / (1 + (eqResult * 0.01));
    }

    public double activeSkillResultFury(List<EntitySkill> charaInfo, double nomalAttackResult) {
        return charaInfo.get(0).getActiveskill() * nomalAttackResult;
    }

    public double ultSkillResultFury(List<EntitySkill> charaInfo, double nomalAttackResult) {
        return charaInfo.get(0).getUltSkill() * nomalAttackResult;
    }


    public double[] eqResult(List<EntityEq> eqInfo) {
        double eqResultSkill = eqInfo.get(0).getSkillSpeedSet() + eqInfo.get(0).getSubSkill().getSkillSpeedHands()
                + eqInfo.get(0).getSubSkill().getSkillSpeedCase() + eqInfo.get(0).getSubSkill().getSkillSpeedGear()
                + eqInfo.get(0).getSubSkill().getSkillSpeedMovement();
        double eqResultAttack = eqInfo.get(0).getAtackSpeedSet() + eqInfo.get(0).getSubAttack().getAtackSpeedHands()
                + eqInfo.get(0).getSubAttack().getAtackSpeedCase() + eqInfo.get(0).getSubAttack().getAtackSpeedGear()
                + eqInfo.get(0).getSubAttack().getAtackSpeedMovement();
        return new double[] { eqResultSkill, eqResultAttack };
    }

}
