package com.stackroute.profileindexing.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.stackroute.profileindexing.model.Gender;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;

@Component
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope= PersonalInfo.class)
public class PersonalInfo implements Serializable {

    private String email;
    private String name;
    private Gender gender;
    private String dob;
    private String currentLocation;
    private String preferredLocation;

    public PersonalInfo() {
    }

    public PersonalInfo(String email, String name, Gender gender, String dob, String currentLocation, String preferredLocation) {
        this.email = email;
        this.name = name;
        this.gender = gender;
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
}
