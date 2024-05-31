package com.counter.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class ServiecController {
    @Autowired
    Repo_Skill_Info skillRepo;

    @Autowired
    Repo_Eq_info eqRepo;

    @Autowired
    ServiecEqMechanism skillMechanism; // 추가된 부분

    @Autowired
    ServiecCharaMecanism charaMecanism;

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

        double[] eqResult = skillMechanism.eqResult(eqInfo,0);

        double nomalAttackResult = skillMechanism.nomalAttackResult(charaInfo, eqResult[1],0);
        double passiveskillResult = skillMechanism.passiveskillResult(charaInfo, nomalAttackResult);

        double activeSkillResult;
        double ultSkillResult;
        if (charaInfo.get(0).getCharaNum() < 98) {
            activeSkillResult = skillMechanism.activeSkillResult(charaInfo, eqResult[0]);
            ultSkillResult = skillMechanism.ultSkillResult(charaInfo, eqResult[0]);
        } else {
            activeSkillResult = skillMechanism.activeSkillResultFury(charaInfo, nomalAttackResult);
            ultSkillResult = skillMechanism.ultSkillResultFury(charaInfo, nomalAttackResult);
        }

        // charaMecanism.calculateResults(charaInfo, eqInfo);
        charaMecanism.calculateSkillUsage(charaInfo, eqInfo, 35.0);

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

    @Transactional
    public void deleteDataByUserId(Long userId) {
        eqRepo.deleteByUserid(userId);
        skillRepo.deleteByUserid(userId);
    }
}
