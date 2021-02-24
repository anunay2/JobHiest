package com.stackroute.jobprofileservice.service;

import com.stackroute.jobprofileservice.controller.ProfileController;
import com.stackroute.jobprofileservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


class RabbitMqSenderTest {
    @Mock
    Logger logger;

    @Mock
    JobSeekerProfileService jobSeekerProfileService;
    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ProfileController profileController;
    private JobSeekerProfile jobSeekerProfile;
    @InjectMocks
    private RabbitMqSender rabbitMqSender;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);

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
                "15-10-1998","Banglore","Pune",skillsList,experienceList,educationList,199.33,appliedJobId);
        //jobSeekerProfileRepository.save(jobSeekerProfile);
        // jobSeekerProfileList.add(jobSeekerProfile);
    }



    @Test
    void testSendExperienceProcessing(){

        rabbitMqSender.sendSkillProcessing(jobSeekerProfile);
    }

    @Test
    void testSendPersonalProcessing() {
        rabbitMqSender.sendPersonalProcessing(jobSeekerProfile);
    }

    @Test
    void testSendSkillProcessing() {
        rabbitMqSender.sendPersonalProcessing(jobSeekerProfile);
    }
}