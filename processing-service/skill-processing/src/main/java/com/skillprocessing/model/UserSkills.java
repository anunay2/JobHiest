package com.skillprocessing.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Map;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = UserSkills.class)
public class UserSkills implements Serializable {

    private String email;
    private Map<String, ProfiencyLevel> skills;

    public UserSkills(String email, Map<String, ProfiencyLevel> skills) {
        this.email = email;
        this.skills = skills;
    }

    public UserSkills() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, ProfiencyLevel> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, ProfiencyLevel> skills) {
        this.skills = skills;
    }
}
