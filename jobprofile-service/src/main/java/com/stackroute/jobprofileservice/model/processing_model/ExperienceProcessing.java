package com.stackroute.jobprofileservice.model.processing_model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.stackroute.jobprofileservice.model.Experience;

import java.io.Serializable;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope= PersonalInfo.class)
public class ExperienceProcessing implements Serializable {
    String email;
    List< Experience > experiences;

    public ExperienceProcessing(){

    }

    public ExperienceProcessing(String email, List< Experience > experiences) {
        this.email = email;
        this.experiences = experiences;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List< Experience > getExperiences() {
        return experiences;
    }

    public void setExperiences(List< Experience > experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return "experienceProcessing{" +
                "email='" + email + '\'' +
                ", experiences=" + experiences +
                '}';
    }
}
