package com.stackroute.jobrecruiterprofileservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;
import com.stackroute.jobrecruiterprofileservice.service.JobRecruiterProfileService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)

class JobRecruiterProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    JobRecruiterProfileService jobRecruiterProfileService;

    @InjectMocks
    private JobRecruiterProfileController jobRecruiterProfileController;
    private JobRecruiterProfile jobRecruiterProfile;
    private List<JobRecruiterProfile> jobRecruiterProfileList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobRecruiterProfileController).build();

        jobRecruiterProfile = new JobRecruiterProfile("shubham@gmail", "Shubham Singh",
                "CGI IT", 9852630891L, 831013L, "GWER23N45",
                JobRecruiterProfile.CompanyType.ANALYTICS, Instant.now().getEpochSecond());

    }

    @AfterEach
    public void tearDown() {
        jobRecruiterProfile = null;
    }


    @Test
    void givenProfiletoSavethenReturnSavedProfile() throws Exception {
        when(jobRecruiterProfileService.saveProfile(any())).thenReturn(jobRecruiterProfile);
        mockMvc.perform(post("/api/v1/rprofile").contentType(MediaType.APPLICATION_JSON).content(asJsonString(jobRecruiterProfile))).andExpect(status().isCreated());
        verify(jobRecruiterProfileService,times(1)).saveProfile(any());
    }

    @Test
    void givenEmailIdShouldupdateProfileThenReturnUpdatedProfile() throws Exception {
        when(jobRecruiterProfileService.updateProfile(any())).thenReturn(jobRecruiterProfile);
        mockMvc.perform(put("/api/v1/rprofile").contentType(MediaType.APPLICATION_JSON).content(asJsonString(jobRecruiterProfile)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(jobRecruiterProfileService,times(1)).saveProfile(any());
    }

    @Test
    void givenEmailIdThenShouldReturnRespectiveProfile() throws Exception {
        when(jobRecruiterProfileService.getProfileEmail(jobRecruiterProfile.getEmail())).thenReturn(jobRecruiterProfile);
        mockMvc.perform(get("/api/v1/rprofile/shubham@gmail"))
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