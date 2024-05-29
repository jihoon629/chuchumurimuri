package com.counter.calculator;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface Repo_Skill_Info extends CrudRepository<EntitySkill, Integer>{
    
    List<EntitySkill> findByuserid(Long userid);

 
    void deleteByUserid(Long userid);
}

