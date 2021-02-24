package com.stackroute.profileindexing.model.dto;

import java.util.HashMap;
import java.util.Map;

public class ProcessedSkill {
    private String email;
    private Map<String, Proficiency> skills = new HashMap<>();

    public ProcessedSkill() {
    }

    public ProcessedSkill(String email, Map<String, Proficiency> skills) {
        this.email = email;
        this.skills = skills;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Proficiency> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Proficiency> skills) {
        this.skills = skills;
    }
}
