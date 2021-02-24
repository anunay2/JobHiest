package com.stackroute.jobprofileservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.jobprofileservice.model.Experience;
import com.stackroute.jobprofileservice.model.JobSeekerProfile;
import com.stackroute.jobprofileservice.model.Skills;
import com.stackroute.jobprofileservice.model.processing_model.PersonalInfo;
import com.stackroute.jobprofileservice.service.JobSeekerProfileService;
import com.stackroute.jobprofileservice.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class ProfileController {
    private JobSeekerProfileService jobSeekerProfileService;
    RabbitMqSender rabbitMqSender;

//
//    @Value("${app.message")
//    private String message;
////
//    @PostMapping(value="experience")
//    public String jobSeekerExpDetails(@RequestBody JobSeekerProfile jobSeekerProfile){
//
//        return message;
//    }

    @Autowired
    public ProfileController(JobSeekerProfileService jobSeekerProfileService, RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }


    @HystrixCommand(fallbackMethod = "saveProfileFallback")
    @PostMapping("/profile")
    public ResponseEntity< JobSeekerProfile > saveProfile(@RequestBody JobSeekerProfile jobSeekerProfile) {
        JobSeekerProfile profile = jobSeekerProfileService.saveProfile(jobSeekerProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }


    @PostMapping("/listOfProfiles")
    public ResponseEntity< ? > saveProfileList(@RequestBody List<JobSeekerProfile> jsps)
    {
        List<JobSeekerProfile> jobSeekerProfileList=new ArrayList<>();
        if(jsps!=null){
            for(int i=0;i<jsps.size();i++){
                JobSeekerProfile j1= jobSeekerProfileService.saveProfile(jsps.get(i));
                jobSeekerProfileList.add(j1);
            }
        }
        return new ResponseEntity<>(jobSeekerProfileList, HttpStatus.CREATED);
    }

    @HystrixCommand(fallbackMethod = "getAllProfilesFallback")
    @GetMapping("/profiles")
    public ResponseEntity< List< JobSeekerProfile > > getAllProfiles() {
        return new ResponseEntity< List< JobSeekerProfile > >((List< JobSeekerProfile >) jobSeekerProfileService.getAllProfile(), HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getProfileFallback")
    @GetMapping("profile/{email}")
    public ResponseEntity<JobSeekerProfile> getProfile(@PathVariable("email") String email){
        JobSeekerProfile profile=jobSeekerProfileService.getProfileEmail(email);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

    /*@GetMapping("profile/{profileId}")
    public ResponseEntity< JobSeekerProfile > getProfileById(@PathVariable("profileId") int profileId) {
        return new ResponseEntity<>(jobSeekerProfileService.getProfileById(profileId), HttpStatus.FOUND);
    }

    @DeleteMapping("profile/{profileId}")
    public ResponseEntity< JobSeekerProfile > getProfileAfterDeletion(@PathVariable("profileId") int profileId) {
        return new ResponseEntity<>(jobSeekerProfileService.deleteProfile(profileId), HttpStatus.OK);
    }

    @PutMapping("profiles")
    public ResponseEntity< ? > update(@RequestBody JobSeekerProfile jobSeekerProfile, @RequestParam(required = false) Object object) {
        JobSeekerProfile updatedprofile = jobSeekerProfileService.updateProfile(jobSeekerProfile);
        if (object instanceof Experience) {
            rabbitMqSender.sendExperienceProcessing(jobSeekerProfile);
        } else if (object instanceof Skills) {
            rabbitMqSender.sendSkillProcessing(jobSeekerProfile);
        } else if (object instanceof PersonalInfo) {
            rabbitMqSender.sendPersonalProcessing(jobSeekerProfile);
        }
        return new ResponseEntity<>(updatedprofile, HttpStatus.OK);
    }*/

    @HystrixCommand(fallbackMethod = "appendtojoblistFallback")
    @PatchMapping("profile/{email}/job/{id}")
    public ResponseEntity<?> appendtojoblist(@PathVariable("id") int id,@PathVariable("email") String email){
            JobSeekerProfile updatedProfile=jobSeekerProfileService.applyForJob(id,email);
            return new ResponseEntity<>(updatedProfile,HttpStatus.ACCEPTED);
    }




    //    @PatchMapping("JobId")
    //    public ResponseEntity<?> applyforJob(@RequestBody JobSeekerProfile profile){
    //        JobSeekerProfile updatedProfile = jobSeekerProfileService.applyForJob(profile);
    //        return new ResponseEntity<>(updatedProfile, HttpStatus.ACCEPTED);
    //    }

    @HystrixCommand(fallbackMethod = "updateProfileFallback")
    @PutMapping("profile")
    public ResponseEntity< JobSeekerProfile > updateProfile(@RequestBody JobSeekerProfile profile) {
        JobSeekerProfile updatedprofile = jobSeekerProfileService.updateProfile(profile);

        return new ResponseEntity<>(updatedprofile, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "deleteProfileFallback")
    @DeleteMapping("profile/{email}")
    public ResponseEntity<JobSeekerProfile> deleteProfile(@PathVariable("email") String email){
        JobSeekerProfile deletedprofile =jobSeekerProfileService.deleteProfileByEmail(email);
        return new ResponseEntity<>(deletedprofile,HttpStatus.OK);
    }
//    @DeleteMapping("profile/{profileId}")
//    public ResponseEntity< JobSeekerProfile > getProfileAfterDeletion(@PathVariable("profileId") int profileId) {
//        return new ResponseEntity<>(jobSeekerProfileService.deleteProfile(profileId), HttpStatus.OK);
//    }



//    @PutMapping("updateExperience")
//    public ResponseEntity<JobSeekerProfile> updateExperience(@RequestBody JobSeekerProfile profile){
//        JobSeekerProfile updatedExperience=jobSeekerProfileService.updateProfile(profile);
//        rabbitMqSender.sendExperienceProcessing(profile);
//        return new ResponseEntity<>(updatedExperience,HttpStatus.OK);
//    }

    /*@PutMapping("updateSkills")
    public ResponseEntity< JobSeekerProfile > updateSkills(@RequestBody JobSeekerProfile profile) {
        JobSeekerProfile updatedSkills = jobSeekerProfileService.updateProfile(profile);
        rabbitMqSender.sendSkillProcessing(profile);
        return new ResponseEntity<>(updatedSkills, HttpStatus.OK);
    }

    @PutMapping("updatePersonalInfo")
    public ResponseEntity< JobSeekerProfile > updatePersonalInfo(@RequestBody JobSeekerProfile profile) {
        JobSeekerProfile updatedPersonalInfo = jobSeekerProfileService.updateProfile(profile);
        rabbitMqSender.sendPersonalProcessing(profile);
        return new ResponseEntity<>(updatedPersonalInfo, HttpStatus.OK);
    }

    @PutMapping("updatedExperienceProcessing")
    public ResponseEntity< JobSeekerProfile > updateExperienceProcessing(@RequestBody JobSeekerProfile profile) {
        JobSeekerProfile updatedExperience = jobSeekerProfileService.updateProfile(profile);
        rabbitMqSender.sendExperienceProcessing(profile);
        return new ResponseEntity<>(updatedExperience, HttpStatus.OK);
    }*/

    //fallbacks below

    public ResponseEntity< JobSeekerProfile > saveProfileFallback(@RequestBody JobSeekerProfile jobSeekerProfile) {
        return new ResponseEntity<>(new JobSeekerProfile("","","",""), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity< List< JobSeekerProfile > > getAllProfilesFallback() {
        return new ResponseEntity<>(Arrays.asList(new JobSeekerProfile("","","","")), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<JobSeekerProfile> getProfileFallback(@PathVariable("email") String email){
        return new ResponseEntity<>(new JobSeekerProfile("","","",""), HttpStatus.EXPECTATION_FAILED);
    }
    public ResponseEntity< JobSeekerProfile > updateProfileFallback(@RequestBody JobSeekerProfile profile) {
        return new ResponseEntity<>(new JobSeekerProfile("","","",""), HttpStatus.EXPECTATION_FAILED);

    }
    public ResponseEntity<JobSeekerProfile> deleteProfileFallback(@PathVariable("email") String email){
        return new ResponseEntity<>(new JobSeekerProfile("","","",""), HttpStatus.EXPECTATION_FAILED);

    }

}
