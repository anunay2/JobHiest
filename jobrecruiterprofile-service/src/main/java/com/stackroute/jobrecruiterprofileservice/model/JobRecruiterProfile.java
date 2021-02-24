package com.stackroute.jobrecruiterprofileservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Document(collection = "jobrecruiterProfile")
@Table(indexes={@Index(columnList = "email")})
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,scope= JobRecruiterProfile.class)
public class JobRecruiterProfile implements Serializable{
    @Id
    private String email;
    private String name;
    private String organisation;
    private Long mob;
    private Long pin;
    private String gst;

    public enum CompanyType{
        SOFTWARE,ANALYTICS,FINANCE,AUTOMOBILE,CONSTRUCTION,STEEL,PHARMA,OTHERS
    }
    @Enumerated(EnumType.STRING)
    private CompanyType domain;

    private Long createdOnTS;

//    constructor for fallback ppurposes


    public JobRecruiterProfile(String name, String organisation) {

        this.name = name;
        this.organisation = organisation;
    }
}
