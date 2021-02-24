package com.stackroute.queryengine.model.JobModel;

import com.stackroute.queryengine.model.Level;

public class Skill {
    private String skill;
    private Level proficiencyLevel;

    public Skill() {
    }

    public Skill(String skill, Level proficiencyLevel) {
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Level getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(Level proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }


}
