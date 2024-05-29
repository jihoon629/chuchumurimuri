package com.counter.calculator;

import java.util.List;

import org.springframework.data.repository.CrudRepository;




public interface Repo_Eq_info extends CrudRepository<EntityEq, Integer>{
    
    
    List<EntityEq> findByuserid(Long userid);

    void deleteByUserid(Long userid);


}
