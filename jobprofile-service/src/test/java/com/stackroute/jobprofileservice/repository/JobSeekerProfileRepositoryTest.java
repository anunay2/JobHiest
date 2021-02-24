package com.stackroute.jobprofileservice.repository;

import com.stackroute.jobprofileservice.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class JobSeekerProfileRepositoryTest {
    @Autowired
    private JobSeekerProfileRepository jobSeekerProfileRepository;
    private JobSeekerProfile jobSeekerProfile;


    @BeforeEach
    public void setUp(){



        Skills skills1 = new Skills("Angular", ProfiencyLevel.INTERMEDIATE);
        List<Skills> skillsList= new ArrayList<>();
        skillsList.add(skills1);

        Education education1 = new Education("SJS","B.Tech","CS",2001,2007);
        List<Education> educationList= new ArrayList<>();
        educationList.add(education1);

        Experience experience = new Experience("SDE","Stackroute","IT","Oil and Natural Gas","Mentor","2020","Ongoing",18);
        List<Experience> experienceList=  new ArrayList<>();
        experienceList.add(experience);
        List<Integer> appliedJobId= new ArrayList<>();
        appliedJobId.add(1);
        appliedJobId.add(2);
        appliedJobId.add(3);
        jobSeekerProfile = new JobSeekerProfile("shreya001@gmail","Shreya Kumari", Gender.Female,
                "15-10-1998","Banglore","Pune",skillsList,experienceList,educationList,19.44,appliedJobId);
        // jobSeekerProfileList.add(jobSeekerProfile);

    }

    @AfterEach
    public void tearDown(){
       //jobSeekerProfileRepository.deleteAll();
        jobSeekerProfile=null;
    }

    @Test
    public void givenEmailShouldReturnLRespectiveProfile(){
        JobSeekerProfile j1;
        Skills skills1 = new Skills("Angular", ProfiencyLevel.INTERMEDIATE);
        List<Skills> skillsList= new ArrayList<>();
        skillsList.add(skills1);

        Education education1 = new Education("SJS","B.Tech","CS",2001,2007);
        List<Education> educationList= new ArrayList<>();
        educationList.add(education1);

        Experience experience = new Experience("SDE","Stackroute","IT","Oil and Natural Gas","Mentor","2020","Ongoing",18);
        List<Experience> experienceList=  new ArrayList<>();
        experienceList.add(experience);
        List<Integer> appliedJobId= new ArrayList<>();
        appliedJobId.add(1);
        appliedJobId.add(2);
        appliedJobId.add(3);
        j1 = new JobSeekerProfile("shreya001@gmail","Shreya Kumari", Gender.Female,
                "15-10-1998","Banglore","Pune",skillsList,experienceList,educationList,10.333,appliedJobId);
       //JobSeekerProfile j2=jobSeekerProfileRepository.save(j1);
//
//       Optional<JobSeekerProfile> optional= Optional.ofNullable(jobSeekerProfileRepository.findByEmail(j1.getEmail()));
//       assertEquals(j1.getAppliedJobId(),optional.get().getAppliedJobId());
//       assertEquals(j1.getCreatedOnTs(),optional.get().getCreatedOnTs());
//       assertEquals(j1.getCurrentLocation(),optional.get().getCurrentLocation());
//       assertEquals(j1.getEmail(),optional.get().getEmail());
//

    }
}