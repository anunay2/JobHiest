package com.stackroute.profileindexing.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.profileindexing.model.*;
import com.stackroute.profileindexing.model.dto.*;
import com.stackroute.profileindexing.model.dto.Proficiency;
import com.stackroute.profileindexing.model.relations.SkillProficiency;
import com.stackroute.profileindexing.repository.JobSeekerRepository;
import com.stackroute.profileindexing.repository.SkillProficiencyRepository;
import com.stackroute.profileindexing.service.JobSeekerIndexingService;
import com.stackroute.profileindexing.service.RabbitMQService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/")
public class JobSeekerController {

    private JobSeekerRepository jobSeekerRepo;
    private SkillProficiencyRepository skillRepo;
    private JobSeekerIndexingService jobService;

    @Autowired
    public JobSeekerController(JobSeekerRepository jobSeekerRepo, SkillProficiencyRepository skillRepo, JobSeekerIndexingService jobService) {
        this.jobSeekerRepo = jobSeekerRepo;
        this.skillRepo = skillRepo;
        this.jobService = jobService;
    }

    public JobSeekerController() {
    }

    @HystrixCommand(fallbackMethod = "addSkillsFallback")
    @GetMapping("skill")
    public ResponseEntity<?> addSkills(){
        Map<String,Proficiency> skillsMap1 = new HashMap<>();
        skillsMap1.put("JavaScript",Proficiency.ADVANCED);
        skillsMap1.put("React",Proficiency.BASIC);
        ProcessedSkill skillpr = new ProcessedSkill("anunay@gmail.com",skillsMap1);
        jobService.skillInfo(skillpr);
        skillsMap1 = new HashMap<>();
        skillsMap1.put("Python",Proficiency.ADVANCED);
        skillsMap1.put("System Design",Proficiency.BASIC);
        skillpr = new ProcessedSkill("deepak@gmail.com",skillsMap1);
        jobService.skillInfo(skillpr);
        skillsMap1 = new HashMap<>();
        skillsMap1.put("Java",Proficiency.ADVANCED);
        skillsMap1.put("Kotlin",Proficiency.BASIC);
        skillpr = new ProcessedSkill("dinesh@gmail.com",skillsMap1);
        jobService.skillInfo(skillpr);
        skillsMap1 = new HashMap<>();
        skillsMap1.put("JavaScript",Proficiency.ADVANCED);
        skillsMap1.put("Material Design",Proficiency.BASIC);
        skillpr = new ProcessedSkill("ayush@gmail.com",skillsMap1);
        jobService.skillInfo(skillpr);

        return new ResponseEntity<>("Skills Added", HttpStatus.CREATED);
    }


    @HystrixCommand(fallbackMethod = "saveJobSeekerFallback")
    @GetMapping("users")
    public ResponseEntity<?> saveJobSeeker(){
        ProcessedExperience exp1 = new ProcessedExperience(0,"Product Engineer", "CGI","Web Development","","","","",0);
        List<ProcessedExperience> expList1 = new ArrayList<>();
        expList1.add(exp1);
        JobSeekerExperienceInfo expinfo1 = new JobSeekerExperienceInfo("anunay@gmail.com",6,expList1);
        jobService.experienceInfo(expinfo1);
        exp1 = new ProcessedExperience(0,"Analyst", "CGI","Machine Learning","","","","",0);
        expList1 = new ArrayList<>();
        expList1.add(exp1);
        expinfo1 = new JobSeekerExperienceInfo("deepak@gmail.com",15,expList1);
        jobService.experienceInfo(expinfo1);
        exp1 = new ProcessedExperience(0,"UI/UX developer", "CGI","Web Development","","","","",0);
        expList1 = new ArrayList<>();
        expList1.add(exp1);
        expinfo1 = new JobSeekerExperienceInfo("ayush@gmail.com",10,expList1);
        jobService.experienceInfo(expinfo1);
        exp1 = new ProcessedExperience(0,"Android Developer", "CGI","App Development","","","","",0);
        expList1 = new ArrayList<>();
        expList1.add(exp1);
        expinfo1 = new JobSeekerExperienceInfo("dinesh@gmail.com",9,expList1);
        jobService.experienceInfo(expinfo1);
        return new ResponseEntity<>("Works", HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "saveJobSeekerProfileFallback")
    @GetMapping("test")
    public ResponseEntity<?> saveJobSeekerProfile() {
        PersonalInfo person = new PersonalInfo("sahil@gmail.com","Sahil Saxena",Gender.Male,"6-5-98","Bhopal",null);
        jobService.personalInfo(person);
//        person = new PersonalInfo("ayush@gmail.com","Ayush",Gender.Male,"6-6-88","Bhopal","Bangalore");
//        jobService.personalInfo(person);
//        person = new PersonalInfo("anunay@gmail.com","Anunay",Gender.Male,"6-7-99","Mumbai","Bangalore");
//        jobService.personalInfo(person);
//        person = new PersonalInfo("deepak@gmail.com","Deepak",Gender.Male,"3-5-98","Chennai","Hyderabad");
//        jobService.personalInfo(person);
//        person = new PersonalInfo("dinesh@gmail.com","Dinesh",Gender.Male,"6-10-99","Kolkata","Hyderabad");
//        jobService.personalInfo(person);
        return new ResponseEntity<>("Works", HttpStatus.CREATED);
        /*
        skillRepo.deleteAllByJobSeekerEmail("ayush@gmail.com");
        return new ResponseEntity<>("Deleted", HttpStatus.CREATED);
        JobSeeker jobSeeker = jobSeekerRepo.findByEmail("abc@email.com");
        if (jobSeeker != null) {
            Domain domain = new Domain("Web Development");
            jobSeeker.addNewDomain(domain);
            Role role = new Role("Software Engineer");
            jobSeeker.addNewRole(role);
            Skill skill = new Skill("Java");
            SkillProficiency skillProficiency = new SkillProficiency(skill, jobSeeker, Proficiency.ADVANCED);
            Location location = new Location("Hyderabad");
            jobSeeker.prefersLocationIn(location);
            WorkingExperience work = new WorkingExperience((double) 5);
            jobSeeker.hasExperience(work); skillRepo.save(skillProficiency)

            return new ResponseEntity<>(jobSeekerRepo.save(jobSeeker), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
             }
    */
    }
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JobSeekerController.class);
    //shortlisting candidates based on their preferred location agianst the location of the job location
    @HystrixCommand(fallbackMethod = "shortlistByLocationFallback")
    @GetMapping("byLocation/{location}")
    public ResponseEntity<?> shortlistByLocation(@PathVariable String location){
        //logger.info("The received message is " + location);
        List<JobSeeker> store = jobService.searchByLocation(location);

        if(store != null){

            return new ResponseEntity<>(store, HttpStatus.OK);
        }
        String err = "no such cadets found";
        logger.info(err);
        return new ResponseEntity<String>(err, HttpStatus.NOT_FOUND);
    }


//    fallbacks below
    public ResponseEntity<?> addSkillsFallback(){
        return new ResponseEntity<>("", HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> saveJobSeekerFallback(){
        return new ResponseEntity<>("", HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> saveJobSeekerProfileFallback() {
        return new ResponseEntity<>("", HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> shortlistByLocationFallback(@PathVariable String location){
        return new ResponseEntity<>(new JobSeeker("", ""), HttpStatus.EXPECTATION_FAILED);
    }
}

