package com.stackroute.jobprofileservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Component
@Document(collection = "jobseekerProfile")
@Table(indexes={@Index(columnList = "email")})
@Data
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope=JobSeekerProfile.class)
public class JobSeekerProfile implements Serializable {
    @Id
    String email;
    String name;
    Gender gender;
    String dob;
    String currentLocation;
    String preferredLocation;

    //@ElementCollection(targetClass = Experience.class)

    List<Skills> skills;
    //@ElementCollection(targetClass=Experience.class)

    List<Experience> experiences;


    List<Education> educations;
    Double createdOnTs;
    List<Integer> appliedJobId;

    public JobSeekerProfile(String email, String name, Gender gender, String dob, String currentLocation, String preferredLocation, List< Skills > skills, List< Experience > experiences, List< Education > educations, Double createdOnTs, List< Integer > appliedJobId) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.currentLocation = currentLocation;
        this.preferredLocation = preferredLocation;
        this.skills = skills;
        this.experiences = experiences;
        this.educations = educations;
        this.createdOnTs = createdOnTs;
        this.appliedJobId = appliedJobId;
    }

//    constructor for fallbac purposes


    public JobSeekerProfile(String name, String dob, String currentLocation, String preferredLocation) {

        this.name = name;

        this.dob = dob;
        this.currentLocation = currentLocation;
        this.preferredLocation = preferredLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public List< Skills > getSkills() {
        return skills;
    }

    public void setSkills(List< Skills > skills) {
        this.skills = skills;
    }

    public List< Experience > getExperiences() {
        return experiences;
    }

    public void setExperiences(List< Experience > experiences) {
        this.experiences = experiences;
    }

    public List< Education > getEducations() {
        return educations;
    }

    public void setEducations(List< Education > educations) {
        this.educations = educations;
    }

    public Double getCreatedOnTs() {
        return createdOnTs;
    }

    public void setCreatedOnTs(Double createdOnTs) {
        this.createdOnTs = createdOnTs;
    }

    public List< Integer > getAppliedJobId() {
        return appliedJobId;
    }

    public void setAppliedJobId(List< Integer > appliedJobId) {
        this.appliedJobId = appliedJobId;
    }
    public JobSeekerProfile(){

    }

    @Override
    public String toString() {
        return "JobSeekerProfile{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dob='" + dob + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", preferredLocation='" + preferredLocation + '\'' +
                ", skills=" + skills +
                ", experiences=" + experiences +
                ", educations=" + educations +
                ", createdOnTs=" + createdOnTs +
                ", appliedJobId=" + appliedJobId +
                '}';
    }
}
