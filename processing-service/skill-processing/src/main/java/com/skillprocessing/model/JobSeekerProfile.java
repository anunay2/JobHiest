package com.skillprocessing.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope=JobSeekerProfile.class)
public class JobSeekerProfile implements Serializable {
    @Id
    int id;
    String username;
//    @Column(unique = true)
    String email;
    String name;
    Gender gender;
    String dob;
    String currentLocation;
    String preferredLocation;

    //@ElementCollection(targetClass = Experience.class)
    @OneToMany(cascade= CascadeType.ALL)
    List<Skills> skills;
    //@ElementCollection(targetClass=Experience.class)
    @OneToMany(cascade = CascadeType.ALL)
    List<Experience> experiences;
    Double createdOnTs;



}

