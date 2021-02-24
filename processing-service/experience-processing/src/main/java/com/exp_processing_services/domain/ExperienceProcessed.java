package com.exp_processing_services.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope= ExperienceProcessing.class)
public class ExperienceProcessed {
    String email;
    int totalExperience;
    List< Experience > experiences;

    public ExperienceProcessed(){

    }

    public ExperienceProcessed(String email, int totalExperience, List<Experience> experiences) {
        this.email = email;
        this.totalExperience = totalExperience;
        this.experiences = experiences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(int totalExperience) {
        this.totalExperience = totalExperience;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return "ExperienceProcessed{" +
                "email='" + email + '\'' +
                ", totalExperience=" + totalExperience +
                ", experiences=" + experiences +
                '}';
    }
}
