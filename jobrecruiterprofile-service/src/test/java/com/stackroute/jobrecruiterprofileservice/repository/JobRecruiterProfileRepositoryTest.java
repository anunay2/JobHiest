package com.stackroute.jobrecruiterprofileservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;


class JobRecruiterProfileRepositoryTest {

    @Autowired
    private JobRecruiterProfileRepository jobRecruiterProfileRepository;
    private JobRecruiterProfile jobRecruiterProfile;

    @BeforeEach
    public void setUp(){
        jobRecruiterProfile = new JobRecruiterProfile("shubham@gmail.com","Shubham Singh",
                "CGI IT", 9852630891L, 831013L, "GWER23N45",
                JobRecruiterProfile.CompanyType.ANALYTICS, Instant.now().getEpochSecond());

    }

    @AfterEach
    public void tearDown(){
        jobRecruiterProfile=null;
    }

    @Test
    public void givenEmailShouldReturnRespectiveProfile(){
        JobRecruiterProfile j1;
        j1= new JobRecruiterProfile("shubham@gmail.com","Shubham Singh",
                "CGI IT", 9852630891L, 831013L, "GWER23N45",
                JobRecruiterProfile.CompanyType.ANALYTICS, Instant.now().getEpochSecond());
    }


//    @Test
//    public void givenProfileToSaveShouldReturnSavedProfile(){
////        JobRecruiterProfile jobRecruiterProfile = new JobRecruiterProfile("shubham@gmail.com","Shubham Singh",
////                "CGI IT", 9852630891L, 831013L, "GWER23N45",
////                JobRecruiterProfile.CompanyType.ANALYTICS, Instant.now().getEpochSecond());
//        jobRecruiterProfileRepository.save(jobRecruiterProfile);
//        JobRecruiterProfile jobRecruiterProfile1=jobRecruiterProfileRepository.findByEmail(jobRecruiterProfile.getEmail());
//        assertEquals(jobRecruiterProfile1,jobRecruiterProfile1);
//
//    }

//
//    @Test
//    public void givenEmailShouldReturnLRespectiveProfile() {
////       jobRecruiterProfile=new JobRecruiterProfile("shubham@gmail.com","Shubham Singh",
////                "CGI IT", 9852630891L, 831013L, "GWER23N45",
////                JobRecruiterProfile.CompanyType.ANALYTICS, Instant.now().getEpochSecond());
//        jobRecruiterProfileRepository.save(jobRecruiterProfile);
//        JobRecruiterProfile j1 =jobRecruiterProfileRepository.findByEmail(jobRecruiterProfile.getEmail());
//        assertEquals(jobRecruiterProfile,j1);
//    }


}