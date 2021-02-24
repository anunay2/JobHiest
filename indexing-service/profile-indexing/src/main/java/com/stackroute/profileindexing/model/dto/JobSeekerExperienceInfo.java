package com.stackroute.profileindexing.model.dto;


import java.util.ArrayList;
import java.util.List;

public class JobSeekerExperienceInfo {
    private String email;
    private int totalExperience;
    private List<ProcessedExperience> experiences = new ArrayList<>();

    public JobSeekerExperienceInfo() {
    }

    public JobSeekerExperienceInfo(String email, int totalExperience, List<ProcessedExperience> experiences) {
        this.email = email;
        this.totalExperience = totalExperience;
        this.experiences = experiences;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ProcessedExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ProcessedExperience> experiences) {
        this.experiences = experiences;
    }
}
