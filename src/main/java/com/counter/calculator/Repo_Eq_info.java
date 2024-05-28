package com.counter.calculator;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;




public interface Repo_Eq_info extends CrudRepository<EntityEq, Integer>{
    
    

@Query("SELECT e FROM EntityEq e WHERE e.id = (SELECT MAX(e2.id) FROM EntitySkill e2)")
List<EntityEq>  siiballlll();

     @Transactional
    @Modifying
    @Query("DELETE FROM EntityEq")
    void deleteAllDataSkillEq();






}
