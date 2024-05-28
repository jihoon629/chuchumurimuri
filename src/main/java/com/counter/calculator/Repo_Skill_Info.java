package com.counter.calculator;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface Repo_Skill_Info extends CrudRepository<EntitySkill, Integer>{
    
    @Query("SELECT e FROM EntitySkill e WHERE e.id = (SELECT MAX(e2.id) FROM EntitySkill e2)")
    List<EntitySkill> siibal();


 
    
     @Transactional
    @Modifying
    @Query("DELETE FROM EntitySkill")
    void deleteAllDataSkill();
}

