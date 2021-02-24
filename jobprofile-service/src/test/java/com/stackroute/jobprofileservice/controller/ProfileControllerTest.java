package com.stackroute.jobprofileservice.controller;

import com.stackroute.jobprofileservice.model.*;
import com.stackroute.jobprofileservice.service.JobSeekerProfileService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    private MockMvc mockMvc;
    @Mock
    JobSeekerProfileService jobSeekerProfileService;


    @InjectMocks
    private ProfileController profileController;
    private JobSeekerProfile jobSeekerProfile;
    private List<JobSeekerProfile> jobSeekerProfileList;



    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();


        Skills skills1 = new Skills("Angular", ProfiencyLevel.INTERMEDIATE);
        List<Skills> skillsList= new ArrayList<>();
        skillsList.add(skills1);

        Education education1 = new Education("SJS","B.Tech","CS",2001,2007);
        List<Education> educationList= new ArrayList<>();
        educationList.add(education1);

        Experience  experience = new Experience("SDE","Stackroute","IT","Oil and Natural Gas","Mentor","2020","Ongoing",18);
        List<Experience> experienceList=  new ArrayList<>();
        experienceList.add(experience);
        List<Integer> appliedJobId= new ArrayList<>();
        appliedJobId.add(1);
        appliedJobId.add(2);
        appliedJobId.add(3);
        jobSeekerProfile = new JobSeekerProfile("shreya001@gmail","Shreya Kumari", Gender.Female,
                "15-10-1998","Banglore","Pune",skillsList,experienceList,educationList,10.333,appliedJobId);
       // jobSeekerProfileList.add(jobSeekerProfile);

    }
    
    @AfterEach
    public void tearDown(){
        jobSeekerProfile=null;
    }

    @Test
    void givenEmailIdShouldupdateProfileThenReturnUpdatedProfile() throws Exception{
        when(jobSeekerProfileService.updateProfile(any())).thenReturn(jobSeekerProfile);
        mockMvc.perform(put("/api/v1/profile").contentType(MediaType.APPLICATION_JSON).content(asJsonString(jobSeekerProfile)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void givenEmailIdThenShouldReturnRespectiveProfile() throws Exception{
        when(jobSeekerProfileService.getProfileEmail(jobSeekerProfile.getEmail())).thenReturn(jobSeekerProfile);
        mockMvc.perform(get("/api/v1/profile/shreya001@gmail"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}