package com.stackroute.jobprofileservice.model.processing_model;


import com.stackroute.jobprofileservice.model.ProfiencyLevel;

import java.util.Map;

public class skillsProcessing{
    String email;
    Map<String, ProfiencyLevel > skills;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map< String, ProfiencyLevel > getSkills() {
        return skills;
    }

    public void setSkills(Map< String, ProfiencyLevel > skills) {
        this.skills = skills;
    }

    public skillsProcessing(String email, Map< String, ProfiencyLevel > skills) {
        this.email = email;
        this.skills = skills;
    }

    public skillsProcessing(){

    }

    @Override
    public String toString() {
        return "skillsProcessing{" +
                "email='" + email + '\'' +
                ", skills=" + skills +
                '}';
    }
}
