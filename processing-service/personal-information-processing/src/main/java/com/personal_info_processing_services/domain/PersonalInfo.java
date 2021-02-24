package com.personal_info_processing_services.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope= PersonalInfo.class)
public class PersonalInfo implements Serializable {


    String email;
    String name;
    Gender sex;
    String dob;
    String currentLocation;
    String preferredLocation;

    public PersonalInfo(){

    }

    public PersonalInfo(String email, String name, Gender sex, String dob, String currentLocation, String preferredLocation) {

        this.email = email;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.currentLocation = currentLocation;
        this.preferredLocation = preferredLocation;
    }

    public Gender getGender() {
        return sex;
    }

    public void setGender(Gender sex) {
        this.sex = sex;
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

    @Override
    public String toString() {
        return "personalInfo{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", dob='" + dob + '\'' +
                ", currentLocation='" + currentLocation + '\'' +
                ", preferredLocation='" + preferredLocation + '\'' +
                '}';
    }
}
