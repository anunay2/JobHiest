package com.stackroute.jobrecruiterprofileservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;
import com.stackroute.jobrecruiterprofileservice.service.JobRecruiterProfileService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class JobRecruiterProfileController {

    @NonNull
    private JobRecruiterProfileService jobRecruiterProfileService;

    @HystrixCommand(fallbackMethod = "saveProfileFallback")
    @PostMapping("/rprofile")
    public ResponseEntity<JobRecruiterProfile> saveProfile(@RequestBody JobRecruiterProfile jobRecruiterProfile){
        JobRecruiterProfile profile = jobRecruiterProfileService.saveProfile(jobRecruiterProfile);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @HystrixCommand(fallbackMethod = "getProfileFallback")
    @GetMapping("rprofile/{email}")
    public ResponseEntity<JobRecruiterProfile> getProfile(@PathVariable("email") String email){
        JobRecruiterProfile profile=jobRecruiterProfileService.getProfileEmail(email);
        return new ResponseEntity<>(profile,HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "updateProfileFallback")
    @PutMapping("rprofile")
    public ResponseEntity<JobRecruiterProfile> updateProfile(@RequestBody JobRecruiterProfile profile) {
        JobRecruiterProfile updatedprofile = jobRecruiterProfileService.updateProfile(profile);

        return new ResponseEntity<>(updatedprofile, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getAllProfilesFallback")
    @GetMapping("/rprofiles")
    public ResponseEntity<List< JobRecruiterProfile >> getAllProfiles() {
        return new ResponseEntity< List< JobRecruiterProfile > >((List< JobRecruiterProfile >) jobRecruiterProfileService.getAllProfile(), HttpStatus.OK);
    }

//    fallbacks below
    public ResponseEntity<JobRecruiterProfile> saveProfileFallback(@RequestBody JobRecruiterProfile jobRecruiterProfile){
      return new ResponseEntity<>(new JobRecruiterProfile("",""), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<JobRecruiterProfile> getProfileFallback(@PathVariable("email") String email){
        return new ResponseEntity<>(new JobRecruiterProfile("",""), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<JobRecruiterProfile> updateProfileFallback(@RequestBody JobRecruiterProfile profile) {
        return new ResponseEntity<>(new JobRecruiterProfile("",""), HttpStatus.EXPECTATION_FAILED);
    }

   public ResponseEntity<List< JobRecruiterProfile >> getAllProfilesFallback() {
        return new ResponseEntity<>(Arrays.asList(new JobRecruiterProfile("","")), HttpStatus.EXPECTATION_FAILED);
   }
}
