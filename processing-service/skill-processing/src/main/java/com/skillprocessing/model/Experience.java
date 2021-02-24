package com.skillprocessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String role;
    String organisation;
    String domain;
    String responsibilities;
    //@OneToMany(cascade=CascadeType.ALL)
    String project;
    String startDate;
    String endDate;
    int months;
}
