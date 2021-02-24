package com.stackroute.jobrecruiterprofileservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;
import com.stackroute.jobrecruiterprofileservice.repository.JobRecruiterProfileRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class JobRecruiterProfileServiceImplTest {
    @Mock
    private JobRecruiterProfileRepository jobRecruiterProfileRepository;

    @InjectMocks
    private JobRecruiterProfileServiceImpl jobRecruiterProfileService;
    private JobRecruiterProfile jobRecruiterProfile;
    private Optional optional;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        jobRecruiterProfile = new JobRecruiterProfile("shubham@gmail.com","Shubham Singh", "CGI IT",
                9852630891L, 831013L, "GWER23N45", JobRecruiterProfile.CompanyType.ANALYTICS,
                Instant.now().getEpochSecond());

    }

    @AfterEach
    public void tearDown(){
        jobRecruiterProfile=null;
    }

    @Test
    public void givenProfiletoSavethenReturnSavedProfile(){
        when(jobRecruiterProfileRepository.save(any())).thenReturn(jobRecruiterProfile);
        assertEquals(jobRecruiterProfile,jobRecruiterProfileService.saveProfile(jobRecruiterProfile));
        verify(jobRecruiterProfileRepository,times(1)).save(any());
    }

    @Test
    void givenProfileEmailshouldreturnProfile(){
        when(jobRecruiterProfileRepository.findByEmail(anyString())).thenReturn(jobRecruiterProfile);
        JobRecruiterProfile retrievedProfile=jobRecruiterProfileService.getProfileEmail(jobRecruiterProfile.getEmail());
        assertEquals("shubham@gmail.com",retrievedProfile.getEmail());
    }


}