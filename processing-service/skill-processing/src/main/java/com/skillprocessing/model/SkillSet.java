package com.skillprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("SkillSet")
public class SkillSet implements Serializable {

    @Id
    private String skill;
    private List<String> secondarySkills;

//    public SkillSet(String skill, List<String> secondarySkills) {
//        this.skill = skill;
//        this.secondarySkills = secondarySkills;
//    }
//
//    public SkillSet() {
//    }
//
//    public String getSkill() {
//        return skill;
//    }
//
//    public void setSkill(String skill) {
//        this.skill = skill;
//    }
//
//    public List<String> getSecondarySkills() {
//        return secondarySkills;
//    }
//
//    public void setSecondarySkills(List<String> secondarySkills) {
//        this.secondarySkills = secondarySkills;
//    }

//    constructor for fallback pruposes

    public SkillSet(String skill) {
        this.skill = skill;
    }
}
