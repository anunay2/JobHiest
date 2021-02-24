package com.stackroute.jobindexing.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.jobindexing.exception.JobAlreadyExistsException;
import com.stackroute.jobindexing.model.GraphModel.*;
import com.stackroute.jobindexing.model.Job;
import com.stackroute.jobindexing.service.JobGraphService;
import com.stackroute.jobindexing.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The controller for the Job Indexing microservice.
*/

@RestController
public class JobController {

//    TODO: Add exceptions
//          Add Test Cases
//          Add documentation and comments


    private JobService jobService;
    private JobGraphService jobGraphService;

    @Autowired
    public JobController(JobService jobService,JobGraphService jobGraphService)
    {
        this.jobService=jobService;
        this.jobGraphService=jobGraphService;

    }
//    @HystrixCommand(fallbackMethod = "postJobApplicationFallback")
    @PostMapping("/jobs")
    public ResponseEntity<Job> postJobApplication(@RequestBody Job job) throws JobAlreadyExistsException
    {
        job.setCreatedOn(Instant.now().getEpochSecond());
        JobGraph jobGraph=convertJobToJobGraph(job);
        JobGraph savedJobGraph=jobGraphService.addJobGraphNode(jobGraph);
        job.setId(jobGraph.getId());
        Job savedJob=jobService.saveJob(job);
        return new ResponseEntity<>(savedJob,HttpStatus.CREATED);
    }


    @PostMapping("/jobs-list")
    public ResponseEntity<?> postJobApplicationsList(@RequestBody List<Job> jobs) throws JobAlreadyExistsException
    {
        List<Job> savedJobs=new ArrayList<>();
        for(Job job:jobs)
        {
            job.setCreatedOn(Instant.now().getEpochSecond());
            JobGraph jobGraph=convertJobToJobGraph(job);
            JobGraph savedJobGraph=jobGraphService.addJobGraphNode(jobGraph);
            job.setId(jobGraph.getId());
            Job savedJob=jobService.saveJob(job);
            savedJobs.add(savedJob);
        }
        return new ResponseEntity<>(savedJobs,HttpStatus.CREATED);

    }
    private JobGraph convertJobToJobGraph(Job job)
    {
        JobGraph jobGraph=new JobGraph(job.getJobTitle(),job.getRecruiterEmail(),job.getOrganisation(),job.getCreatedOn());
        jobGraph.isLocatedAt(new WorkingLocation(job.getWorkingLocation()));
        jobGraph.requiresExperience(new Experience(job.getCandidateProfile().getTotalExperience().getMinValue(),job.getCandidateProfile().getTotalExperience().getMaxValue()));
        jobGraph.requiresAgeRange(new Age(job.getCandidateProfile().getAge().getMinValue(),job.getCandidateProfile().getAge().getMaxValue()));
        job.getCandidateProfile().getSkill().forEach((skill)->{
            jobGraph.requiresSkill(new SkillNode(skill.getSkill(),skill.getProficiencyLevel()));
        });
        jobGraph.setExpectedSalary(new Salary(job.getSalary().getMinValue(),job.getSalary().getMaxValue()));
        job.getCandidateProfile().getDomains().forEach((domain)->{
            jobGraph.requiresDomain(new Domain(domain));
        });
        job.getCandidateProfile().getRoles().forEach((role)->{
            jobGraph.requiresRole(new Role(role));
        });
        return jobGraph;
    }

//    fallbacks below
//    public ResponseEntity<Job> postJobApplicationFallback(@RequestBody Job job){
//        return new ResponseEntity<>(new Job("", ""), HttpStatus.EXPECTATION_FAILED);
//    }



}
