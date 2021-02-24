package com.stackroute.queryengine.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.queryengine.model.JobGraph.JobGraph;
import com.stackroute.queryengine.model.JobGraph.SkillNode;
import com.stackroute.queryengine.model.JobGraph.WorkingLocation;
import com.stackroute.queryengine.model.JobModel.Job;
import com.stackroute.queryengine.model.JobSeekerGraph.JobSeeker;
import com.stackroute.queryengine.model.JobSeekerGraph.Location;
import com.stackroute.queryengine.model.JobSeekerGraph.Skill;
import com.stackroute.queryengine.repository.JobGraphRepository;
import com.stackroute.queryengine.repository.JobModelRepository;
import com.stackroute.queryengine.repository.JobSeekerGraphRepository;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class QueryController {

//    TODO: Add Pagination and Sorting in all the GET APIs
//          Add exception
//          Add Test Cases
//          Add documentation and comments

    private JobGraphRepository jobGraphRepository;
    private JobSeekerGraphRepository jobSeekerGraphRepository;
    private JobModelRepository jobModelRepository;

    @Autowired
    public QueryController(JobGraphRepository jobGraphRepository,JobSeekerGraphRepository jobSeekerGraphRepository,JobModelRepository jobModelRepository)
    {
        this.jobGraphRepository=jobGraphRepository;
        this.jobSeekerGraphRepository=jobSeekerGraphRepository;
        this.jobModelRepository=jobModelRepository;
    }

    @HystrixCommand(fallbackMethod = "getAllJobSeekersFallback")
    @GetMapping("/job-seekers")
    public ResponseEntity<?> getAllJobSeekers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    )
    {
        Pageable page=PageRequest.of(pageNumber,pageSize, Sort.by(sortingElement));
        Page<JobSeeker> pagedResult=jobSeekerGraphRepository.findAll(page);
        List<JobSeeker> jobSeekerList=pagedResult.getContent();
        return new ResponseEntity<>(jobSeekerList,HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getAllJobsPostedFallback")
    @GetMapping("/job-postings")
    public ResponseEntity<?> getAllJobsPosted(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    )
    {
        Pageable page=PageRequest.of(pageNumber,pageSize, Sort.by(sortingElement));
        Page<JobGraph> pagedResult=jobGraphRepository.findAll(page);
        List<JobGraph> jobsList=pagedResult.getContent();
        return new ResponseEntity<>(jobsList,HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getJobPostingsByRecruiterEmailFallback")
    @GetMapping("/job-postings/{email}")
    public ResponseEntity<?> getJobPostingsByRecruiterEmail(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    )
    {
        Pageable page=PageRequest.of(pageNumber,pageSize,Sort.by(sortingElement));
        Page<JobGraph> pagedResult=jobGraphRepository.findByRecruiterEmail(email,page);
        List<JobGraph> jobGraphList=pagedResult.getContent();
        return new ResponseEntity<>(jobGraphList,HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getRecommendedJobsFallback")
    @GetMapping("/recommended-jobs/{email}")
    public ResponseEntity<List<JobGraph>> getRecommendedJobs(@PathVariable String email,
                                                             @RequestParam(defaultValue = "0") Integer pageNumber,
                                                             @RequestParam(defaultValue = "10") Integer pageSize)
    {
        if(jobSeekerGraphRepository.findByEmail(email).size()==0) return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        JobSeeker jobSeeker=jobSeekerGraphRepository.findByEmail(email).get(0);
        Set<Skill> skillSet=jobSeeker.getSkillsPossessed();
        Set<Location> locations=jobSeeker.getLocations();
        List<String> skillList=new ArrayList<>();
        List<String> locationList=new ArrayList<>();
        for(Skill skill:skillSet)
        {
            skillList.add(skill.getSkillName());
        }
        for(Location location:locations)
        {
            locationList.add(location.getPreferredLocation());
        }
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Page<JobGraph> pagedResult=jobGraphRepository.findRecommendedJobs(skillList,locationList,pageable);
        List<JobGraph> recommendedJobs=pagedResult.getContent();


        return new ResponseEntity<>(recommendedJobs, HttpStatus.OK);

    }

    @HystrixCommand(fallbackMethod = "getRecommendedProfilesFallback")
    @GetMapping("/recommended-profiles/{id}")
    public ResponseEntity<List<JobSeeker>> getRecommendedProfiles(@PathVariable Long id,
                                                                  @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize)

    {
        JobGraph jobGraph=jobGraphRepository.findById(id).get();
        Set<SkillNode> skillNodes=jobGraph.getSkillsRequired();
        List<String> skillList=new ArrayList<>();

        for(SkillNode skill:skillNodes)
        {
            skillList.add(skill.getSkillName());
        }
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Page<JobSeeker> pagedResult=jobSeekerGraphRepository.findRecommendedProfiles(skillList,pageable);
        List<JobSeeker> recommendedProfiles=pagedResult.getContent();
        List<JobSeeker> recommendedProfilesWithInfo=new ArrayList<>();
        for(JobSeeker jobSeeker:recommendedProfiles)
            recommendedProfilesWithInfo.add(jobSeekerGraphRepository.findByEmail(jobSeeker.getEmail()).get(0));
        return new ResponseEntity<>(recommendedProfilesWithInfo,HttpStatus.OK);

    }

    @HystrixCommand(fallbackMethod = "getAllJobsFallback")
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs()
    {
        return new ResponseEntity<>(jobModelRepository.findAll(),HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getJobDetailsByIdFallback")
    @GetMapping("/job-details/{id}")
    public ResponseEntity<Job> getJobDetailsById(@PathVariable Long id)
    {
        return new ResponseEntity<>(jobModelRepository.findById(id).get(),HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "getJobsByNameFallback")
    @GetMapping("/search-by-name/{jobName}")
    public ResponseEntity<?> getJobsByName(
            @PathVariable String jobName,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    )
    {
        Pageable page=PageRequest.of(pageNumber,pageSize,Sort.by(sortingElement));
        Page<JobGraph> pagedResult=jobGraphRepository.findByJobNameContaining(jobName,page);
        List<JobGraph> jobGraphList=pagedResult.getContent();
        return new ResponseEntity<>(jobGraphList,HttpStatus.OK);
    }
//    @GetMapping("/search-by-location/{location}")
//    public ResponseEntity<?> getJobsByLocation(
//            @PathVariable String location,
//            @RequestParam(defaultValue = "0") Integer pageNumber,
//            @RequestParam(defaultValue = "10") Integer pageSize,
//            @RequestParam(defaultValue = "id") String sortingElement
//    )
//    {
//        Pageable page=PageRequest.of(pageNumber,pageSize,Sort.by(sortingElement));
//        Page<JobGraph> pagedResult=jobGraphRepository.findByLocationsAvailableName(location,page);
//        List<JobGraph> jobGraphList=pagedResult.getContent();
//        return new ResponseEntity<>(jobGraphList,HttpStatus.OK);
//    }

    @HystrixCommand(fallbackMethod = "getJobsByTitleLocationSkillFallback")
    @GetMapping("/jobs-search")
    public ResponseEntity<?> getJobsByTitleLocationSkill(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String skill,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    )
    {
        if(title.isEmpty()&&skill.isEmpty()&&location.isEmpty()) return new ResponseEntity<>("Enter something..!",HttpStatus.OK);
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Page<JobGraph> pagedResult=jobGraphRepository.searchByTitleLocationSkill(title.toLowerCase(),skill.toLowerCase(),location.toLowerCase(),pageable);
        List<JobGraph> jobGraphList=pagedResult.getContent();
        return new ResponseEntity<>(jobGraphList,HttpStatus.OK);
    }

//    fallbacks below

    public ResponseEntity<?> getAllJobSeekersFallback(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    ){
        List<JobSeeker> jobSeekerList = new ArrayList<>();
        return new ResponseEntity<>(jobSeekerList, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> getAllJobsPostedFallback(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    ){
        List<JobGraph> jobGraphList = new ArrayList<>();
        return new ResponseEntity<>(jobGraphList, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> getJobPostingsByRecruiterEmailFallback(
            @PathVariable String email,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    ){
        List<JobGraph> jobGraphList = new ArrayList<>();
        return new ResponseEntity<>(jobGraphList, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<List<JobGraph>> getRecommendedJobsFallback(@PathVariable String email,
                                                             @RequestParam(defaultValue = "0") Integer pageNumber,
                                                             @RequestParam(defaultValue = "10") Integer pageSize){
        List<JobGraph> jobGraphList = new ArrayList<>();
        return new ResponseEntity<>(jobGraphList, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<List<JobSeeker>> getRecommendedProfilesFallback(@PathVariable Long id,
                                                                  @RequestParam(defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(defaultValue = "10") Integer pageSize){
        List<JobSeeker> jobSeekerList = new ArrayList<>();
        return new ResponseEntity<>(jobSeekerList, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<List<Job>> getAllJobsFallback(){

        return new ResponseEntity<>(Arrays.asList(new Job("","","","", "", "")), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<Job> getJobDetailsByIdFallback(@PathVariable Long id){
        return new ResponseEntity<>(new Job("err","","","", "", ""), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> getJobsByNameFallback(
            @PathVariable String jobName,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortingElement
    ){
        List<JobGraph> jobGraphList = new ArrayList<>();
        return new ResponseEntity<>(jobGraphList, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> getJobsByTitleLocationSkillFallback(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String skill,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize
    ){
        List<JobGraph> jobGraphList = new ArrayList<>();
        return new ResponseEntity<>(jobGraphList, HttpStatus.EXPECTATION_FAILED);
    }

}
