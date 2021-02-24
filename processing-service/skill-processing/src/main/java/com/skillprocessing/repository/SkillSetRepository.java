package com.skillprocessing.repository;


import com.skillprocessing.model.SkillSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SkillSetRepository{


//    private HashOperations hashOperations;
    public static final String HASH_KEY = "SkillSet";

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    public SkillSetRepository(RedisTemplate<String, SkillSet> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        hashOperations = redisTemplate.opsForHash();
//    }


    public SkillSet save(SkillSet skillSet) {
//        hashOperations.put(HASH_KEY,skillSet, skillSet);
        redisTemplate.opsForHash().put(HASH_KEY, skillSet.getSkill(), skillSet);
        return skillSet;
    }


    public SkillSet getSubSkills(String skill) {

//        return (SkillSet) hashOperations.get(HASH_KEY,skill);
        if(redisTemplate.opsForHash().hasKey(HASH_KEY,skill)){
            return (SkillSet)redisTemplate.opsForHash().get(HASH_KEY,skill);
        }
        return null;

    }
}
