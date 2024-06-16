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

        double[] eqResult = skillMechanism.eqResult(eqInfo, 0,0);

        double nomalAttackResult = skillMechanism.nomalAttackResult(charaInfo, eqResult[1], 0);
        double passiveskillResult = skillMechanism.passiveskillResult(charaInfo, nomalAttackResult);

        double activeSkillResult;
        double ultSkillResult;
        if (charaInfo.get(0).getCharaNum() < 96) {
            activeSkillResult = skillMechanism.activeSkillResult(charaInfo, eqResult[0]);
            ultSkillResult = skillMechanism.ultSkillResult(charaInfo, eqResult[0]);
        } else {
            activeSkillResult = skillMechanism.activeSkillResultFury(charaInfo, nomalAttackResult);
            ultSkillResult = skillMechanism.ultSkillResultFury(charaInfo, nomalAttackResult);
        }

        double[] dealtime = charaMecanism.calculateDealTime(charaInfo, eqInfo, 30);
        
        System.out.println("30초 동안 평타 사용 횟수: " + dealtime[0]);
        System.out.println("30초 동안 패시브 사용 횟수: " + dealtime[1]);
        System.out.println("30초 동안 특수기 사용 횟수: " + dealtime[2]);
        System.out.println("30초 동안 궁극기 사용 횟수: " + dealtime[3]);

        model.addAttribute("normalAttackCount", dealtime[0]);
        model.addAttribute("passiveSkillCount", dealtime[1]);
        model.addAttribute("activeSkillCount", dealtime[2]);
        model.addAttribute("ultSkillCount", dealtime[3]);

        model.addAttribute("nomalAttact",charaInfo.get(0).getNomalAttack());
        model.addAttribute("passiveSkill",Math.round(charaInfo.get(0).getPassiveskill() * charaInfo.get(0).getNomalAttack() * 10) / 10.0);
        
        if(charaInfo.get(0).getCharaNum() < 96) {
            model.addAttribute("activeSkill", charaInfo.get(0).getActiveskill());
        model.addAttribute("ultSkill", charaInfo.get(0).getUltSkill());

        } else {
            model.addAttribute("activeSkill", charaInfo.get(0).getActiveskill()*charaInfo.get(0).getNomalAttack());
            model.addAttribute("ultSkill", charaInfo.get(0).getUltSkill()*charaInfo.get(0).getNomalAttack());    
        }
        model.addAttribute("nomalAttackResult", nomalAttackResult);
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
