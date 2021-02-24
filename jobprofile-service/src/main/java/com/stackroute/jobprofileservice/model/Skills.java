package com.stackroute.jobprofileservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//@Document(collection = "skills")
public class Skills{

    String skill;
    ProfiencyLevel profiencyLevel;

    public Skills(){

    }

    public Skills( String skill, ProfiencyLevel profiencyLevel) {

        this.skill = skill;
        this.profiencyLevel = profiencyLevel;
    }



    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public ProfiencyLevel getProfiencyLevel() {
        return profiencyLevel;
    }

    public void setProfiencyLevel(ProfiencyLevel profiencyLevel) {
        this.profiencyLevel = profiencyLevel;
    }

    @Override
    public String toString() {
        return "Skills{" +
                ", skill='" + skill + '\'' +
                ", profiencyLevel=" + profiencyLevel +
                '}';
    }
}
