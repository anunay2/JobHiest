package com.stackroute.profileindexing.service;

import com.stackroute.profileindexing.model.*;
import com.stackroute.profileindexing.model.dto.*;
import com.stackroute.profileindexing.model.relations.SkillProficiency;
import com.stackroute.profileindexing.repository.JobSeekerRepository;
import com.stackroute.profileindexing.repository.SkillProficiencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RabbitMQService implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQService.class);
    private JobSeekerIndexingService jobService;

    public RabbitMQService(){}

    @Autowired
    public RabbitMQService(JobSeekerIndexingService jobService) {
        this.jobService = jobService;
    }

    /*
     * Fetch the queue names from yml file, and names should be in sync with processing services
     * verify queue names and processed info models from processing services
     */
    @RabbitListener(queues = "${spring.rabbitmq.personalQ}")
    public void getPersonalInfo(PersonalInfo personalInfo){
        logger.info("The received message is " + personalInfo.toString());
        jobService.personalInfo(personalInfo);
        //Check whether job seeker is already present or not and write an if condition
        /*JobSeeker jobSeeker = new JobSeeker(personalInfo.getEmail(),personalInfo.getDob(), personalInfo.getName(), personalInfo.getGender());
        Location preferred = new Location(personalInfo.getPreferredLocation());
        jobSeeker.prefersLocationIn(preferred);
        jobSeekerRepo.save(jobSeeker);
        //Query to Create Relationhip between jobseeker and preferred location
        //if location already exists, just create a relation
        */
    }

//
    @RabbitListener(queues = "${spring.rabbitmq.experienceQ}")
    public void getExperienceInfo(JobSeekerExperienceInfo processedExperience){
        jobService.experienceInfo(processedExperience);
        //fetch Job Seeker through emailId
        /*JobSeeker jobSeeker;
        Role role;
        Domain domain;
        WorkingExperience workingExperience = new WorkingExperience(processedExperience.getTotalExperience());
        jobSeeker = jobSeekerRepo.findByEmail(processedExperience.getEmail()); //Check if optional or not
        if(jobSeeker == null){
            jobSeeker = new JobSeeker();
            jobSeeker.setEmail(processedExperience.getEmail());
            jobSeeker.hasExperience(workingExperience);
        }
        jobSeeker.hasExperience(workingExperience);
        for(ProcessedExperience experience : processedExperience.getJobSeekerExperiencesList()){
            role = new Role(experience.getRole());
            domain = new Domain(experience.getDomainName());
            jobSeeker.addNewRole(role);
            jobSeeker.addNewDomain(domain);
        }
        jobSeekerRepo.save(jobSeeker);

        //Create a WorkExperience Node if its not present and then create a relation
        //Create a Domain Node if its not present and then create a relation
        //Create a Role Node if its not present and then create a relation
        */
        logger.info("The received message is " + processedExperience.toString());
    }

    @RabbitListener(queues = "${spring.rabbitmq.skillQ}")
    public void skillInfo(ProcessedSkill skillMap){
        jobService.skillInfo(skillMap);
        //fetch the jobseeker node with the particular username or email
        /*JobSeeker jobSeeker;
        Skill newSkill;
        SkillProficiency proficiencyLevel;
        jobSeeker = jobSeekerRepo.findByEmail(skillMap.getEmail()); //Check if optional or not
        if(jobSeeker == null){
            jobSeeker = new JobSeeker();
            jobSeeker.setEmail(skillMap.getEmail());
        }
        for(Skill skill : skillMap.getCandidateSkills().keySet()){
            newSkill = new Skill(skill.getSkillName());
            proficiencyLevel = new SkillProficiency(newSkill,jobSeeker,skillMap.getCandidateSkills().get(skill.getSkillName()));
            skillProficiencyRepo.save(proficiencyLevel);
        }*/
        logger.info("The received message is " + skillMap.toString());
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}

